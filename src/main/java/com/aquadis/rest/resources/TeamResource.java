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
        System.out.println("Before team resource");
        service = RepositoryServiceImpl.getInstance();
        System.out.println("After team resource");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> getAllTeams() {
        System.out.println("Before get all teams");
        return service.getAllTeams();
    }

    @GET
    @Path("/{teamID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeamFromId(@PathParam("teamID") int teamID) {
        System.out.println("Before get team from id");
        Team team = service.getTeamFromId(teamID);

        System.out.println("Checks if team exists");
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find team with id: " + teamID)).build();
        }

        System.out.println("Build team");
        return Response.status(Response.Status.OK).entity(team).build();
    }

    @GET
    @Path("/{teamID}/racers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRacersFromTeam(@PathParam("teamID") int teamID) {
        System.out.println("Before get team from id");
        Team team = service.getTeamFromId(teamID);

        System.out.println("Check if team exists");
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find team with id: " + teamID)).build();
        }

        System.out.println("Before get racers from team");
        List<Racer> racers = service.getRacersFromTeam(teamID);

        System.out.println("Build racers");;
        return Response.status(Response.Status.OK).entity(racers).build();
    }
}
