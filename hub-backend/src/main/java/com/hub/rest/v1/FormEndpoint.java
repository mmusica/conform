package com.hub.rest.v1;

import com.hub.mongo.dto.FormDto;
import com.hub.mongo.service.FormService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import lombok.RequiredArgsConstructor;

@Path("/v1/forms")
@RequiredArgsConstructor
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class FormEndpoint {

    final FormService service;

    @POST
    public Response createForm(FormDto dto) {
        service.persist(dto);
        return Response.status(Status.CREATED.getStatusCode())
                .build();
    }

    @GET
    public Response getFormByUser(@QueryParam("user") String user) {
        return Response.ok()
                .entity(service.findUsersForms(user))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getFormById(@PathParam("id") String id) {
        return Response.ok()
                .entity(service.findById(id))
                .build();
    }
}
