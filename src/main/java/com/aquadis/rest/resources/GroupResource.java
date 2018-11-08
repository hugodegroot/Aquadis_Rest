package com.aquadis.rest.resources;

import com.aquadis.models.Group;
import com.aquadis.models.User;
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
@Path("/")
public class GroupResource {

    private RepositoryService service;

    public GroupResource() {
        service = RepositoryServiceImpl.getInstance();
    }

    /**
     * Returns a list with all the groups of a user
     * at: http://localhost:8080/aquadis/rest/users/{userID}/groups
     *
     * @param userID
     * @return list of groups of a user
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGroups(@PathParam("userID") int userID) {
        User user = service.getUserFromId(userID);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with id: " + userID)).build();
        }

        List<Group> groups = service.getGroupsFromUser(user);

        return Response.status(Response.Status.OK)
                .entity(groups).build();
    }

    /**
     * Returns a specific group object based on its ID.
     * at: http://localhost:8080/aquadis/rest/users/{userID}/groups/{groupID}
     *
     * @param userID
     * @param groupID
     * @return
     */
    @GET
    @Path("{groupID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupFromUser(@PathParam("userID") int userID, @PathParam("groupID") int groupID) {

        Group group = service.getGroupFromId(userID, groupID);

        if (group == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find group with id: " + groupID + ". In user with id: " + userID))
                    .build();
        }

        return Response.status(Response.Status.OK)
                .entity(group).build();
    }
}
