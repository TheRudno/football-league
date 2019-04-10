package pl.polsl.take.footballleague.rest;


import pl.polsl.take.footballleague.ApplicationConfig;
import pl.polsl.take.footballleague.exceptions.ElementNotFoundException;
import pl.polsl.take.footballleague.exceptions.ElementValidationException;
import pl.polsl.take.footballleague.service.FootballerServiceBean;
import pl.polsl.take.footballleague.database.Footballer;
import pl.polsl.take.footballleague.dto.ErrorDTO;
import pl.polsl.take.footballleague.dto.FootballerDTO;
import pl.polsl.take.footballleague.dto.FootballerListDTO;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/footballer")
public class FootballerREST {

    @EJB
    FootballerServiceBean footballerService;

    @GET
    @Path("/")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getAll() {
        List<Footballer> footballerList = footballerService.getAll();
        return Response
                .ok()
                .entity(FootballerListDTO.from(footballerList))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getById(@PathParam("id") Long id) {
        try{
            Footballer footballer= footballerService.getById(id);
            return Response
                    .ok()
                    .entity(FootballerDTO.from(footballer))
                    .build();
        }catch(ElementNotFoundException exception){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }catch(Exception exception){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }
    }

    @GET
    @Path("/{id}/remove")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response removeById(@PathParam("id")Long id){
        try{
            footballerService.remove(id);
            return Response
                    .noContent()
                    .build();
        }catch(ElementNotFoundException exception){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }
    }


    @Inject
    Validator validator;


    @POST
    @Path("/add")
    @Consumes(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response add(FootballerDTO footballer){
        try{

            footballerService.add(footballer.toFootballer());
            return Response
                    .noContent()
                    .build();
        }catch(ElementValidationException exception){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }
    }

    @POST
    @Path("/{id}/update")
    @Consumes(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response update(@PathParam("id")Long id,Footballer footballer){
        try {
            footballer.setId(id);
            footballerService.update(footballer);
            return Response
                    .noContent()
                    .build();
        }catch(ElementNotFoundException exception){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }catch(Exception exception){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }
    }
}