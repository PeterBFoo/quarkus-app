package org.pingpong.onequarkusapp.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "t_ordenes")
public class Orden extends PanacheEntityBase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id")
    private Long ord_id;

    @OneToOne
    @JoinColumn(name = "ord_user")
    private Usuaria user;

    @OneToOne
    @JoinColumn(name = "ord_item")
    private Items item;

    public Orden() {}

    public Orden(Usuaria user, Items item) {
        this.user = user;
        this.item = item;
    }

    public Long getOrd_id() {
        return ord_id;
    }

    public void setOrd_id(Long ord_id) {
        this.ord_id = ord_id;
    }

    public Usuaria getUser() {
        return user;
    }

    public void setUser(Usuaria user) {
        this.user = user;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public static List<Orden> findByUserName(String username) {
        List<Orden> allOrders = Orden.listAll();
        List<Orden> allOrdersOfUser = allOrders.stream().filter(order -> order.getUser().getNombre().equals(username)).collect(Collectors.toList());
        return allOrdersOfUser.isEmpty()? List.of() : allOrdersOfUser;
    }
}
