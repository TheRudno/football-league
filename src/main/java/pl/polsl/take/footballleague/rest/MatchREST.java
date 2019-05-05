package pl.polsl.take.footballleague.rest;

import pl.polsl.take.footballleague.ApplicationConfig;
import pl.polsl.take.footballleague.database.Footballer;
import pl.polsl.take.footballleague.database.Goal;
import pl.polsl.take.footballleague.database.Match;
import pl.polsl.take.footballleague.dto.ErrorDTO;
import pl.polsl.take.footballleague.dto.FootballerDTO;
import pl.polsl.take.footballleague.dto.MatchDTO;
import pl.polsl.take.footballleague.dto.MatchListDTO;
import pl.polsl.take.footballleague.exceptions.ElementNotFoundException;
import pl.polsl.take.footballleague.exceptions.ElementValidationException;
import pl.polsl.take.footballleague.exceptions.ConversionException;
import pl.polsl.take.footballleague.service.ClubServiceBean;
import pl.polsl.take.footballleague.service.GoalServiceBean;
import pl.polsl.take.footballleague.service.MatchServiceBean;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/match")
public class MatchREST {

    @EJB
    GoalServiceBean goalService;

    @EJB
    MatchServiceBean matchService;

    @EJB
    ClubServiceBean clubService;

    @GET
    @Path("/")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getAll() {
        List<Match> matches = matchService.getAll();
        return Response
                .ok()
                .entity(MatchListDTO.from(matches))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getById(@PathParam("id") Long id) {
        try{
            Match match= matchService.getById(id);
            return Response
                    .ok()
                    .entity(MatchDTO.from(match))
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

    @POST
    @Path("/add")
    @Consumes(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response add(MatchDTO match){
        try{
            match.setId(null);
            Match newMatch = match.toMatch();
            if(match.getHomeSide()!=null)
                newMatch.setHomeSide(clubService.getById(match.getHomeSide()));
            if(match.getHomeSide()!=null)
                newMatch.setAwaySide(clubService.getById(match.getAwaySide()));

            List<Goal> goals = new ArrayList<>();
            if(match.getGoals()!=null){
                for (Long l:  match.getGoals()){
                    goals.add(goalService.getById(l));
                }
            }
            newMatch.setGoals(goals);
            matchService.add(newMatch);
            return Response
                    .noContent()
                    .build();
        }catch(ElementValidationException exception){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        } catch (IllegalArgumentException | ElementNotFoundException | ConversionException exception) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }
    }

    @GET
    @Path("/result")
    @Consumes(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getResults(){
        return Response
                .ok()
                .entity(Match.Result.values())
                .build();
    }

    @POST
    @Path("/{id}/update")
    @Consumes(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response update(@PathParam("id")Long id, MatchDTO match){
        try {
            Match target = matchService.getById(id);
            target.setHomeSide(clubService.getById(match.getHomeSide()));
            target.setAwaySide(clubService.getById(match.getAwaySide()));
            List<Goal> goals=  new ArrayList<>();
            for (Long l:  match.getGoals()){
                goals.add(goalService.getById(l));
            }
            target.setGoals(goals);
            matchService.update(match.mergeWith(target));
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
        }catch(ConversionException exception){
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
            matchService.remove(id);
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
}
