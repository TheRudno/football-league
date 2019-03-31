package pl.polsl.take.footballleague.test;


import javax.ws.rs.*;
@Path("/echo")

public class ItShouldWorkREST{

    @Path("/{echo}")
    @GET
    public String echo(@PathParam("echo") String echo) {
        return "Halko halko "+echo+".";
    }
}
