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
        service = RepositoryServiceImpl.getInstance();
    }

    /**
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Race> getAllRacers() {
        return service.getAllRaces();
    }

    /**
     * @return
     */
    @GET
    @Path("/race")
    @Produces(MediaType.APPLICATION_JSON)
    public Race getCurrentRace() {
        return service.getCurrentRace();
    }

    /**
     * @param race
     * @return
     */
    @POST
    @Path("/race")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Race addRAce(Race race) {
        return service.addRace(race);
    }

    /**
     * @param raceID
     * @return
     */
    @GET
    @Path("/{raceID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRaceFromId(@PathParam("raceID") int raceID) {
        Race race = service.getRaceFromId(raceID);

        if (race == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find race with id: " + raceID)).build();
        }

        return Response.status(Response.Status.OK)
                .entity(race).build();
    }

    /**
     * @param raceID
     * @return
     */
    @GET
    @Path("/{raceID}/positions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRacePositionsFromRace(@PathParam("raceID") int raceID) {
        Race race = service.getRaceFromId(raceID);

        if (race == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find race with id: " + raceID)).build();
        }

        List<RacePosition> racePositions = service.getRacePositionsFromRace(raceID);

        return Response.status(Response.Status.OK)
                .entity(racePositions).build();
    }
}
