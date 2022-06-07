package org.pingpong.onequarkusapp;

import org.pingpong.onequarkusapp.dominio.Items;
import org.pingpong.onequarkusapp.dominio.Orden;
import org.pingpong.onequarkusapp.dominio.Usuaria;
import org.pingpong.onequarkusapp.services.ServiceOlli;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/")
public class ResourcesOlli {

    @Inject
    ServiceOlli service;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/wellcome")
    public String wellcome() {
        return "Wellcome Ollivanders!";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/usuaria/{nombre}")
    public Response getPersona(@PathParam("nombre") String nombre) {
        Usuaria usuaria = service.cargaUsuaria(nombre);

        if (usuaria.getNombre() != "") {
            return Response.ok(usuaria).status(200).build();
        } else {
            return Response.noContent().status(404).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/ordena")
    public Response postOrder(@Valid Orden orden) {
        Orden orden1 = service.comanda(orden.getUser().getNombre(), orden.getItem().getNombre());
        if (orden1 != null) {
            return Response.ok().status(201).entity(orden1).build();
        } else {
            return Response.noContent().status(404).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pedidos/{usuaria}")
    public Response pedidosUsuaria(@PathParam("usuaria") String usuaria) {
        List<Orden> ordens = service.cargaOrden(usuaria);
        if (!ordens.isEmpty()) {
            return Response.ok(ordens).status(200).build();
        } else {
            return Response.noContent().status(404).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/item/{item}")
    public Response getItem(@PathParam("item") String itemName) {
        Optional<Items> item = Optional.of(service.cargaItem(itemName));
        if (item.get().getNombre() != "") {
            return Response.ok(item).status(200).build();
        } else {
            return Response.noContent().status(404).build();
        }
    }

    
}
