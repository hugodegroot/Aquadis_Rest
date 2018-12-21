package com.aquadis.rest.resources;

import com.aquadis.models.Racer;
import com.aquadis.rest.model.ClientError;
import com.aquadis.service.RepositoryService;
import com.aquadis.service.impl.RepositoryServiceImpl;

import javax.ws.rs.*;
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
        System.out.println("Before racer resource");
        service = RepositoryServiceImpl.getInstance();
        System.out.println("After racer resource");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Racer> getAllRacers() {
        System.out.println("Before get all racers");
        return service.getAllRacers();
    }

    @PUT
    @Path("/racer")
    @Produces(MediaType.APPLICATION_JSON)
    public Racer updateSalary(@QueryParam("id") int racerID, @QueryParam("salary") int salary) {
        System.out.println("Before update racers salary");
        return service.updateRacerSalary(racerID, salary);
    }

    @GET
    @Path("/{racerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRacerFromId(@PathParam("racerID") int racerID) {
        System.out.println("Find racer with id " + racerID);
        Racer racer = service.getRacerFromId(racerID);

        System.out.println("Check if racer exist");
        if (racer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find racer with id: " + racerID)).build();
        }

        System.out.println("Build racer");
        return Response.status(Response.Status.OK)
                .entity(racer).build();
    }
}
