package org.pingpong.onequarkusapp.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_users")
public class Usuaria extends PanacheEntityBase {

    @Id
    @NotNull
    @Column(name = "user_nom")
    private String nombre;

    @Column(name = "user_prop")
    private int destreza;

    public Usuaria() {}

    public Usuaria(String userName, int userProp) {
        this.nombre = userName;
        this.destreza = userProp;
    }

    public int getDestreza() {
        return destreza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setUser_nom(String user_nom) {
        this.nombre = user_nom;
    }

    public void setUser_prop(int user_prop) {
        this.destreza = user_prop;
    }
}
