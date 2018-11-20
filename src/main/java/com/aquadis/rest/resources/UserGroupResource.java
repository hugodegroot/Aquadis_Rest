package com.aquadis.rest.resources;

import com.aquadis.models.Group;
import com.aquadis.models.User;
import com.aquadis.models.UserGroup;
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
@Path("/")
public class UserGroupResource {
    private RepositoryService service;

    public UserGroupResource() {
        service = RepositoryServiceImpl.getInstance();
    }

    @GET
    @Path("/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserGroup> getAllUserGroups(@PathParam("ID") int ID){
        return service.getAllUserGroups(ID);
    }

    @GET
    @Path("/groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsFromUser(@PathParam("userID") int userID){
        User user = service.getUserFromId(userID);

        if (user == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with id: " + userID)).build();
        }

        List<UserGroup> userGroups = service.getAllUserGroupsFromUser(userID);

        return Response.status(Response.Status.OK).entity(userGroups).build();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsFromGroup(@PathParam("groupID") int groupID){
        Group group= service.getGroupFromId(groupID);

        if (group == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find group with id: " + groupID)).build();
        }

        List<UserGroup> userGroups = service.getAllUserGroupsFromGroup(groupID);

        return Response.status(Response.Status.OK).entity(userGroups).build();
    }
}
