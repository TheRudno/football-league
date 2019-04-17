package pl.polsl.take.footballleague.rest;


import pl.polsl.take.footballleague.ApplicationConfig;
import pl.polsl.take.footballleague.database.Goal;
import pl.polsl.take.footballleague.dto.GoalListDTO;
import pl.polsl.take.footballleague.service.GoalServiceBean;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/goal")
public class GoalREST {

    @EJB
    GoalServiceBean goalService;


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
}
