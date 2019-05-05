package pl.polsl.take.footballleague.rest;

import pl.polsl.take.footballleague.ApplicationConfig;
import pl.polsl.take.footballleague.database.Club;
import pl.polsl.take.footballleague.database.Footballer;
import pl.polsl.take.footballleague.dto.ClubDTO;
import pl.polsl.take.footballleague.dto.ClubListDTO;
import pl.polsl.take.footballleague.dto.ErrorDTO;
import pl.polsl.take.footballleague.dto.FootballerListDTO;
import pl.polsl.take.footballleague.exceptions.ElementNotFoundException;
import pl.polsl.take.footballleague.exceptions.ElementValidationException;
import pl.polsl.take.footballleague.service.ClubServiceBean;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/club")
public class ClubREST {

    @EJB
    ClubServiceBean clubService;

    @GET
    @Path("/")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getAll() {
        List<Club> footballerList = clubService.getAll();
        return Response
                .ok()
                .entity(ClubListDTO.from(footballerList))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getById(@PathParam("id") Long id) {
        try{
            Club club= clubService.getById(id);
            return Response
                    .ok()
                    .entity(ClubDTO.from(club))
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
            clubService.remove(id);
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
    public Response add(ClubDTO club){
        try{
            club.setId(null);
            clubService.add(club.toClub());
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
    public Response update(@PathParam("id")Long id,ClubDTO club){
        try {
            Club target = clubService.getById(id);
            clubService.update(club.mergeWith(target));
            return Response
                    .noContent()
                    .build();
        }catch(ElementNotFoundException exception){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }catch(ElementValidationException exception) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }
    }

    @GET
    @Path("/{id}/squad")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getSquad(@PathParam("id")Long id){
        try{
            Club club = clubService.getById(id);
            Set<Footballer> squad = club.getSquad();
            return Response
                    .ok()
                    .entity(FootballerListDTO.from(squad))
                    .build();
        }catch(ElementNotFoundException exception){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }
    }
}
