package com.hub.rest.v1;

import com.hub.mongo.dto.FormResponseDto;
import com.hub.mongo.service.FormResponseService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import lombok.RequiredArgsConstructor;

@Path("/v1/form-response")
@RequiredArgsConstructor
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class FormResponseEndpoint {

    final FormResponseService service;

    @POST
    public Response createFormResponse(@Valid FormResponseDto formResponseDto) {
        service.save(formResponseDto);
        return Response.status(Status.CREATED.getStatusCode()).build();
    }

    @GET
    public Response getFormResponse(@QueryParam("user") String user, @QueryParam("formId") String formId) {
        return Response.ok()
                .entity(service.findUserResponses(user, formId))
                .build();
    }
}
