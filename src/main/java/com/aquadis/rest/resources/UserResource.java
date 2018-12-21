package com.aquadis.rest.resources;

import com.aquadis.models.User;
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
@Path("/users")
public class UserResource {

    private RepositoryService service;

    public UserResource() {
        System.out.println("Before user resource.");
        service = RepositoryServiceImpl.getInstance();
        System.out.println("After user resource.");
    }

    /**
     * Return a list of all the user objects.
     * at: http://localhost:8080/aquadis/rest/users
     *
     * @return user objects list
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        System.out.println("Before get all users.");
        return service.getAllUsers();
    }

    /**
     * Returns a specific user object based on its ID.
     * at: http://localhost:8080/aquadis/rest/users/{userID}
     *
     * @param userID specific user
     * @return user object
     */
    @GET
    @Path("/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFromID(@PathParam("userID") int userID) {
        System.out.println("Find user with id " + userID);
        User user = service.getUserFromId(userID);

        System.out.println("Check if user exist.");
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with id: " + userID)).build();
        }

        System.out.println("Build user.");
        return Response.status(Response.Status.OK)
                .entity(user).build();
    }

    /**
     * @param email
     * @param password
     * @return
     */
    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(
            @QueryParam("email") String email,
            @QueryParam("password") String password) {
        System.out.println("Before get user from login fields");
        User user = service.getUserFromLoginFields(email, password);

        System.out.println("Check user.");
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with email: " + email + "and password: " + password)).build();
        }

        System.out.println("Build user.");
        return Response.status(Response.Status.OK)
                .entity(user).build();

    }

    /**
     * @param email
     * @param firstName
     * @param lastName
     * @return
     */
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersByEmailOrName(
            @QueryParam("email") String email,
            @QueryParam("firstname") String firstName,
            @QueryParam("firstname") String lastName) {

        List<User> users = service.getUsersByEmailOrName(email, firstName, lastName);

        if (users == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with email: " + email + "or name: " + firstName + " " + lastName)).build();
        }

        return Response.status(Response.Status.OK)
                .entity(users).build();

    }

    /**
     * Adds a user to the database
     *
     * @param user specific user
     * @return added user
     */
    @POST
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User user) {
        System.out.println("Before add user.");
        return service.addUser(user);
    }

    /**
     * Returns a group resource
     * at: http://localhost:8080/aquadis/rest/users/{userID}/groups
     *
     * @return a new group resource
     */
    @Path("/{userID}/ug")
    public UserGroupResource getUserGroupResource() {
        System.out.println("Creat new user group.");
        return new UserGroupResource();
    }
}
