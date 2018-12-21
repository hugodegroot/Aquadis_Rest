package com.aquadis.rest.resources;

import com.aquadis.models.MaxPrediction;
import com.aquadis.models.Position;
import com.aquadis.models.Race;
import com.aquadis.models.User;
import com.aquadis.rest.model.ClientError;
import com.aquadis.service.RepositoryService;
import com.aquadis.service.impl.RepositoryServiceImpl;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/maxpredictions")
public class MaxPredictionResource {

    private RepositoryService service;

    public MaxPredictionResource() {
        service = RepositoryServiceImpl.getInstance();
    }

    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MaxPrediction> getAllMaxPredictions() {
        return service.getAllMaxPredictions();
    }

    /**
     *
     * @param userId
     * @param raceId
     * @return
     */
    @GET
    @Path("/mp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaxPredictionFromId(@QueryParam("userId") int userId, @QueryParam("raceId") int raceId) {
        MaxPrediction maxPrediction = service.getMaxPredictionsFromId(userId, raceId);

        if (maxPrediction == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find maxPrediction with userId: " + userId + "and raceId: " + raceId)).build();
        }

        return Response.status(Response.Status.OK)
                .entity(maxPrediction).build();
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    @POST
    @Path("/maxprediction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MaxPrediction addMaxPrediction(JsonObject jsonObject) {
        System.out.println("Before add max prediction");

        // Get the values of the JSON object
        int userId = jsonObject.getInt("userMax");
        int raceId = jsonObject.getInt("raceMax");
        int positionId = jsonObject.getInt("position");

        // Creates a user
        System.out.println("Before get user from id");
        User user = service.getUserFromId(userId);

        // Creates a race
        System.out.println("Before get race from id");
        Race race = service.getRaceFromId(raceId);

        // Creates a position
        System.out.println("Before get position from id");
        Position position = service.getPositionFromId(positionId);

        // Adds a new max prediction to the database
        return service.addMaxPrediction(new MaxPrediction(user, race, position));
    }
}