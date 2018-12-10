package com.aquadis.rest.resources;

import com.aquadis.models.Group;
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
@Path("/groups")
public class GroupResource {

    private RepositoryService service;

    public GroupResource() {
        service = RepositoryServiceImpl.getInstance();
    }

    /**
     * Returns a list with all the groups of a user
     * at: http://localhost:8080/aquadis/rest/users/{userID}/groups
     *
     * @return list of groups of a user
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Group> getAllGroups() {
        return service.getAllGroups();
    }

    /**
     * Returns a specific group object based on its ID.
     * at: http://localhost:8080/aquadis/rest/groups/{groupID}
     *
     * @param groupID specific group
     * @return group
     */
    @GET
    @Path("/{groupID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupFromID(@PathParam("groupID") int groupID) {
        Group group = service.getGroupFromId(groupID);

        if (group == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with id: " + groupID)).build();
        }

        return Response.status(Response.Status.OK)
                .entity(group).build();
    }

    @GET
    @Path("/{groupID}/predictions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPredictionsFromGroup(@PathParam("groupID") int groupID) {
        // TODO: show the predictions of that group
        return null;
    }

    /**
     * Adds a group to the database
     *
     * @param group specific group
     * @return added group
     */
    @POST
    @Path("/group")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Group addGroup(Group group) {
        return service.addGroup(group);
    }


    /**
     * Adds a group to the database
     *
     * @return added group
     */
    @Path("/{groupID}/ug")
    public UserGroupResource getUserGroupResource() {
        return new UserGroupResource();
    }
}
