package com.aquadis.rest.resources;

import com.aquadis.models.Racer;
import com.aquadis.models.Team;
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
@Path("/teams")
public class TeamResource {

    private RepositoryService service;

    public TeamResource() {
        service = RepositoryServiceImpl.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> getAllUsers() {
        return service.getAllTeams();
    }

    @GET
    @Path("/{teamID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeamFromId(@PathParam("teamID") int teamID) {
        Team team = service.getTeamFromId(teamID);

        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find team with id: " + teamID)).build();
        }

        return Response.status(Response.Status.OK).entity(team).build();
    }

    @GET
    @Path("/{teamID}/racers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRacersfromTeam(@PathParam("teamID") int teamID) {
        Team team = service.getTeamFromId(teamID);

        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find team with id: " + teamID)).build();
        }

        List<Racer> racers = service.getRacersFromTeam(teamID);

        return Response.status(Response.Status.OK).entity(racers).build();
    }
}
