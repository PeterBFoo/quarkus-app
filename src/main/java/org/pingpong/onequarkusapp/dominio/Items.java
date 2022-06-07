package org.pingpong.onequarkusapp.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_items")
public class Items extends PanacheEntityBase {

    @Id
    @NotNull
    @Column(name = "item_nom")
    private String nombre;

    @Column(name = "item_prop")
    private int item_prop;

    @Column(name = "item_tipo")
    private String item_tipo;

    public Items() {}

    public Items(String itemName, int itemProp) {
        this.nombre = itemName;
        this.item_prop = itemProp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setItem_nom(String item_nom) {
        this.nombre = item_nom;
    }

    public int getQuality() {
        return item_prop;
    }

    public void setItem_prop(int item_prop) {
        this.item_prop = item_prop;
    }

    public String getTipo() {
        return item_tipo;
    }

    public void setItem_tipo(String item_tipo) {
        this.item_tipo = item_tipo;
    }
}
