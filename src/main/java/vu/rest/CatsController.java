package vu.rest;

import lombok.Getter;
import lombok.Setter;
import vu.businessLogic.CatsInShelter;
import vu.entities.Cat;
import vu.persistence.CatsDAO;
import vu.persistence.SheltersDAO;
import vu.rest.contracts.CatsDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@ApplicationScoped
@Path("/cats")
public class CatsController {

    @Inject
    @Setter
    @Getter
    private CatsDAO catsDAO;

    @Inject
    private SheltersDAO sheltersDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Cat cat = catsDAO.findOne(id);
        if (cat == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CatsDto catDto = new CatsDto();
        catDto.setName(cat.getName());
        catDto.setAge(cat.getAge());
        catDto.setShelterId(cat.getShelter().getId());

        return Response.ok(catDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer catId,
            CatsDto catData) {
        try {
            Cat existingCat = catsDAO.findOne(catId);
            if (existingCat == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingCat.setName(catData.getName());
            existingCat.setAge(catData.getAge());
            existingCat.setShelter(sheltersDAO.findOne(catData.getShelterId()));
            catsDAO.update(existingCat);

            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/newCat")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response create( @FormParam("age") Integer age, @FormParam("name") String name, @FormParam("shelter") Integer shelterId) {
        Cat newCat = new Cat();
        newCat.setName(name);
        newCat.setAge(age);
        newCat.setShelter(sheltersDAO.findOne(shelterId));

        catsDAO.persist(newCat);

        return Response.ok(Response.Status.OK).build();
    }
}