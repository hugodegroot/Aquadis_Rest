package com.aquadis.rest.resources;

import com.aquadis.models.Racer;
import com.aquadis.rest.model.ClientError;
import com.aquadis.service.RepositoryService;
import com.aquadis.service.impl.RepositoryServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Lorenzo
 */
@Path("/racers")
public class RacerResource {
    private RepositoryService service;

    public RacerResource() {
        service = RepositoryServiceImpl.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Racer> getAllRacers(){
        return service.getallRacers();
    }

    @GET
    @Path("/{racerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFromId(@PathParam("racerID")int racerID){
        Racer racer = service.getRacerFromId(racerID);

        if (racer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find racer with id: " + racerID)).build();
        }

        return Response.status(Response.Status.OK)
                .entity(racer).build();
    }
}
