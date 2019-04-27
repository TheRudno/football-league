package pl.polsl.take.footballleague.rest;


import pl.polsl.take.footballleague.ApplicationConfig;
import pl.polsl.take.footballleague.database.Club;
import pl.polsl.take.footballleague.database.Footballer;
import pl.polsl.take.footballleague.dto.ErrorDTO;
import pl.polsl.take.footballleague.dto.FootballerDTO;
import pl.polsl.take.footballleague.dto.FootballerListDTO;
import pl.polsl.take.footballleague.exceptions.ElementNotFoundException;
import pl.polsl.take.footballleague.exceptions.ElementValidationException;
import pl.polsl.take.footballleague.exceptions.NoEnumConstantException;
import pl.polsl.take.footballleague.service.ClubServiceBean;
import pl.polsl.take.footballleague.service.FootballerServiceBean;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/footballer")
public class FootballerREST {

    @EJB
    FootballerServiceBean footballerService;

    @EJB
    ClubServiceBean clubService;

    @GET
    @Path("/")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getAll() {
        Set<Footballer> footballerList = footballerService.getAll();
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

    @POST
    @Path("/add")
    @Consumes(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response add(FootballerDTO footballer){
        try{
            footballer.setId(null);
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
    public Response update(@PathParam("id")Long id,FootballerDTO footballer){
        try {
            Footballer target = footballerService.getById(id);
            footballerService.update(footballer.mergeWith(target));
            return Response
                    .noContent()
                    .build();
        }catch(ElementNotFoundException exception){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }catch(ElementValidationException exception){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }catch(NoEnumConstantException exception){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }

    }

    @GET
    @Path("/{pid}/update/club/{cid}")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response assignClub(@PathParam("pid") Long pid, @PathParam("cid") Long cid){
        try {
            Footballer footballer = footballerService.getById(pid);
            Club club = clubService.getById(cid);
            footballer.setClub(club);
            footballerService.update(footballer);
            return Response
                    .noContent()
                    .build();
        } catch (ElementNotFoundException exception) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ErrorDTO.from(exception))
                    .build();
        } catch (ElementValidationException exception) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }
    }

    @GET
    @Path("/{pid}/update/club/null")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response assignClub(@PathParam("pid") Long pid){
        try {
            Footballer footballer = footballerService.getById(pid);
            footballer.setClub(null);
            footballerService.update(footballer);
            return Response
                    .noContent()
                    .build();
        } catch (ElementNotFoundException exception) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ErrorDTO.from(exception))
                    .build();
        } catch (ElementValidationException exception) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }
    }


    @GET
    @Path("/positions")
    @Consumes(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getPositions(){
        return Response
                .ok()
                .entity(Footballer.PlayerPosition.values())
                .build();
    }

}