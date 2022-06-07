package org.pingpong.onequarkusapp.services;

import org.pingpong.onequarkusapp.dominio.Orden;
import org.pingpong.onequarkusapp.dominio.Usuaria;
import org.pingpong.onequarkusapp.dominio.Items;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServiceOlli {

    public Usuaria cargaUsuaria(String userName) {
        Optional<Usuaria> usuaria = Usuaria.findByIdOptional(userName);
        return usuaria.isPresent()? usuaria.get() : new Usuaria("", 0);
    }

    public Items cargaItem(String itemName) {
        Optional<Items> item = Items.findByIdOptional(itemName);
        return item.isPresent()? item.get() : new Items ("", 0);
    }

    public List<Orden> cargaOrden(String userName) {
        return Orden.findByUserName(userName);
    }

    @Transactional
    public Orden comanda(String userName, String itemName) {
        Orden orden = null;
        Optional<Usuaria> usuaria = Usuaria.findByIdOptional(userName);
        Optional<Items> item = Items.findByIdOptional(itemName);

        if ((usuaria.isPresent() && item.isPresent()) && usuaria.get().getDestreza() > item.get().getQuality()) {
            orden = new Orden(usuaria.get(), item.get());
            orden.persist();
        }

        return orden;
    }

    @Transactional
    public List<Orden> comandaMultiple(String userName, List<String> items) {
        Usuaria usuaria = cargaUsuaria(userName);
        List<Orden> ordenMultiple = new ArrayList<>();
        if (!usuaria.getNombre().isEmpty()) {
            for (String item : items) {
                if (Items.findByIdOptional(item).isPresent()) {
                    ordenMultiple.add(comanda(userName, item));
                }
            }
        }
        return ordenMultiple;
    }
}
