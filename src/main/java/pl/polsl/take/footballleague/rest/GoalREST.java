package pl.polsl.take.footballleague.rest;


import pl.polsl.take.footballleague.ApplicationConfig;
import pl.polsl.take.footballleague.database.Goal;
import pl.polsl.take.footballleague.dto.ErrorDTO;
import pl.polsl.take.footballleague.dto.GoalDTO;
import pl.polsl.take.footballleague.dto.GoalListDTO;
import pl.polsl.take.footballleague.exceptions.ElementNotFoundException;
import pl.polsl.take.footballleague.exceptions.ElementValidationException;
import pl.polsl.take.footballleague.exceptions.ConversionException;
import pl.polsl.take.footballleague.service.FootballerServiceBean;
import pl.polsl.take.footballleague.service.GoalServiceBean;
import pl.polsl.take.footballleague.service.MatchServiceBean;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/goal")
public class GoalREST {

    @EJB
    GoalServiceBean goalService;

    @EJB
    MatchServiceBean matchService;

    @EJB
    FootballerServiceBean footballerService;

    @GET
    @Path("/")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getAll() {
        List<Goal> goals = goalService.getAll();
        return Response
                .ok()
                .entity(GoalListDTO.from(goals))
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response add(GoalDTO goal){
        try{
            goal.setId(null);
            Goal newGoal = goal.toGoal();
            if(goal.getScorer()!=null)
                newGoal.setScorer(footballerService.getById(goal.getScorer()));
            if(goal.getMatch()!=null)
                newGoal.setMatch(matchService.getById(goal.getMatch()));
            goalService.add(newGoal);
            return Response
                    .noContent()
                    .build();
        }catch(ElementValidationException exception){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        } catch (ConversionException | ElementNotFoundException exception) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorDTO.from(exception))
                    .build();
        }
    }

    @GET
    @Path("/team")
    @Consumes(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response getPositions(){
        return Response
                .ok()
                .entity(Goal.Team.values())
                .build();
    }

    @POST
    @Path("{id}/update")
    @Consumes(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response update(@PathParam("id")Long id, GoalDTO goal){
        try {
            Goal target = goalService.getById(id);
            target.setScorer(footballerService.getById(goal.getScorer()));
            target.setMatch(matchService.getById(goal.getMatch()));
            goalService.update(goal.mergeWith(target));
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

    @DELETE
    @Path("/{id}/remove")
    @Produces(ApplicationConfig.DEFAULT_MEDIA_TYPE)
    public Response removeById(@PathParam("id")Long id){
        try{
            goalService.remove(id);
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
