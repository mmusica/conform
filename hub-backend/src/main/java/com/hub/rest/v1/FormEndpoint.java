package com.hub.rest.v1;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import lombok.RequiredArgsConstructor;

import com.hub.form.dto.FormDto;
import com.hub.form.service.FormService;

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

@Path("/v1/forms")
@RequiredArgsConstructor
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@Authenticated
public class FormEndpoint {

    final FormService service;
    final SecurityIdentity identity;

    @POST
    public Response createForm(@Valid FormDto dto) {
        service.save(dto);
        return Response.status(Status.CREATED.getStatusCode())
                .build();
    }

    @GET
    @Path(value = "/me")
    public Response getFormByUser() {
        return Response.ok()
                .entity(service.findUsersForms(identity.getPrincipal().getName()))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getFormById(@PathParam("id") Integer id) {
        return Response.ok()
                .entity(service.findById(id))
                .build();
    }
}
