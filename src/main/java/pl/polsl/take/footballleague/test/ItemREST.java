package pl.polsl.take.footballleague.test;


import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/item")
public class ItemREST {

    @EJB
    ItemEJB itemEJB;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getAll(){
        return itemEJB.get();
    }

    @Path("/add/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean add(@PathParam("name") String name){
        Item item = new Item();
        item.setName(name);
        itemEJB.create(item);
        return true;
    }
}
