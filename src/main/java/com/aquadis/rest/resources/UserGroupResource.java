package com.aquadis.rest.resources;

import com.aquadis.models.Group;
import com.aquadis.models.Race;
import com.aquadis.models.User;
import com.aquadis.models.UserGroup;
import com.aquadis.rest.model.ClientError;
import com.aquadis.service.RepositoryService;
import com.aquadis.service.impl.RepositoryServiceImpl;

import javax.json.JsonObject;
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
    public List<UserGroup> getAllUserGroups(@PathParam("ID") int ID) {
        System.out.println("Before get all user groups");
        return service.getAllUserGroups(ID);
    }

    @POST
    @Path("/usergroup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserGroup addUserGroup(JsonObject jsonObject){

        int userId = jsonObject.getInt("userId");
        int raceId = jsonObject.getInt("raceId");

        User user = service.getUserFromId(userId);
        Group group = service.getLastAddedGroup();
        Race race = service.getRaceFromId(raceId);

        UserGroup userGroup = new UserGroup(0, "admin", user, group, race);

        System.out.println("Before add usergroup");
        return service.addUserGroup(userGroup);
    }

    @GET
    @Path("/groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsFromUser(@PathParam("userID") int userID) {
        System.out.println("Before get user from id");
        User user = service.getUserFromId(userID);

        System.out.println("Check if user exists");
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with id: " + userID)).build();
        }

        System.out.println("Before get all usergroups from user");
        List<UserGroup> userGroups = service.getAllUserGroupsFromUser(userID);

        System.out.println("Build user groups");
        return Response.status(Response.Status.OK).entity(userGroups).build();
    }

    @GET
    @Path("/groups/{groupID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupFromUser(@PathParam("userID") int userID, @PathParam("groupID") int groupID) {
        System.out.println("Before get user from id");
        User user = service.getUserFromId(userID);

        System.out.println("Check if user exists");
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with id: " + userID)).build();
        }

        System.out.println("Before get group from id");
        Group group = service.getGroupFromId(groupID);

        System.out.println("Check if group exists");
        if (group == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find group with id: " + groupID)).build();
        }

        System.out.println("Build group");
        return Response.status(Response.Status.OK).entity(group).build();
    }

    @GET
    @Path("/groups/{groupID}/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersPointsFromGroup(@PathParam("userID") int userID, @PathParam("groupID") int groupID) {
        System.out.println("Before get user from id");
        User user = service.getUserFromId(userID);

        System.out.println("Check if user exists");
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with id: " + userID)).build();
        }

        System.out.println("Before get group from id");
        Group group = service.getGroupFromId(groupID);

        System.out.println("Check if group exists");
        if (group == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find group with id: " + groupID)).build();
        }

        List<UserGroup> users = group.getUsers();
        users.sort((o1, o2) -> o2.getPoints() - o1.getPoints());

        System.out.println("Build users");
        return Response.status(Response.Status.OK).entity(users).build();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersFromGroup(@PathParam("groupID") int groupID) {
        System.out.println("Before get group from id");
        Group group = service.getGroupFromId(groupID);

        System.out.println("Check if group exists");
        if (group == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find group with id: " + groupID)).build();
        }

        System.out.println("Before get all user groups from group");
        List<UserGroup> userGroups = service.getAllUserGroupsFromGroup(groupID);

        System.out.println("Build user groups");
        return Response.status(Response.Status.OK).entity(userGroups).build();
    }
}
