package com.aquadis.rest.resources;

import com.aquadis.models.Race;
import com.aquadis.models.RacePosition;
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
@Path("/races")
public class RaceResource {

    private RepositoryService service;

    public RaceResource() {
        System.out.println("Before race resource");
        service = RepositoryServiceImpl.getInstance();
        System.out.println("After race resource");
    }

    /**
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Race> getAllRaces() {
        System.out.println("Before get all races");
        return service.getAllRaces();
    }

    /**
     * @return
     */
    @GET
    @Path("/race")
    @Produces(MediaType.APPLICATION_JSON)
    public Race getCurrentRace() {
        System.out.println("Before get current race");
        return service.getCurrentRace();
    }

    /**
     * @param raceID
     * @return
     */
    @GET
    @Path("/{raceID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRaceFromId(@PathParam("raceID") int raceID) {
        System.out.println("Before get race from id");
        Race race = service.getRaceFromId(raceID);

        System.out.println("Check if race exists");
        if (race == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find race with id: " + raceID)).build();
        }

        System.out.println("Build race");
        return Response.status(Response.Status.OK)
                .entity(race).build();
    }

    /**
     * @param race
     * @return
     */
    @POST
    @Path("/race")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Race addRace(Race race) {
        System.out.println("Before add race");
        return service.addRace(race);
    }


    /**
     * @param raceID
     * @return
     */
    @GET
    @Path("/{raceID}/positions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRacePositionsFromRace(@PathParam("raceID") int raceID) {
        System.out.println("Before get race from id");
        Race race = service.getRaceFromId(raceID);

        System.out.println("Check if race exists");
        if (race == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find race with id: " + raceID)).build();
        }

        System.out.println("Before get race positions from race");
        List<RacePosition> racePositions = service.getRacePositionsFromRace(raceID);

        System.out.println("Build race positions");
        return Response.status(Response.Status.OK)
                .entity(racePositions).build();
    }
}
