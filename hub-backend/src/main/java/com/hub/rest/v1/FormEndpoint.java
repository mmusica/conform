package com.hub.rest.v1;

import com.hub.mongo.dto.FormDto;
import com.hub.mongo.service.FormService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import lombok.RequiredArgsConstructor;

@Path("/v1/form")
@RequiredArgsConstructor
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class FormEndpoint {

    final FormService service;

    @POST
    public Response createForm(@Valid FormDto dto) {
        service.save(dto);
        return Response.status(Status.CREATED.getStatusCode())
                .build();
    }

    @GET
    @Path("/{user}")
    public Response createForm(@PathParam("user") String user) {
        return Response.ok()
                .entity(service.findUsersForms(user))
                .build();
    }
}
