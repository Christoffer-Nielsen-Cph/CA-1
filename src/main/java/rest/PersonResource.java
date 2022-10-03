package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import datafacades.PersonFacade;
import dtos.PersonDTO;
import entities.Person;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {

        return Response.ok().entity(GSON.toJson(FACADE.getAllPeople())).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id) throws EntityNotFoundException {
        PersonDTO p = FACADE.getPersonById(id);
        return Response.ok().entity(GSON.toJson(p)).build();
    }

/*
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String content) {
        PersonDTO personDTO = GSON.fromJson(content, PersonDTO.class);
        Person newPdto = FACADE.create(personDTO);
        return Response.ok().entity(GSON.toJson(newPdto)).build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") int id, String content) throws EntityNotFoundException {
        PersonDTO pdto = GSON.fromJson(content, PersonDTO.class);
        pdto.setPerson_Id(id);
        PersonDTO updated = FACADE.update(pdto);
        return Response.ok().entity(GSON.toJson(updated)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") int id) throws EntityNotFoundException {
        PersonDTO deleted = FACADE.delete(id);
        return Response.ok().entity(GSON.toJson(deleted)).build();
    }

    */
}
