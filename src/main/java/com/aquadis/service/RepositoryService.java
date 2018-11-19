package com.aquadis.service;

import com.aquadis.models.*;

import java.util.List;

// TODO: set explanaition at all the parameters!

/**
 * @author Lorenzo
 */
public interface RepositoryService {

    // All the methods of the users
    /**
     * Getting all users
     *
     * @return list of users
     */
    List<User> getAllUsers();

    /**
     * Getting a specific user
     *
     * @param userID
     * @return a user
     */
    User getUserFromId(int userID);

    /**
     * validate user by username and password
     *
     * @param username
     * @param password
     * @return
     */
    User getUserFromloginFields(String username, String password);

    /**
     * Adding a user into the database
     *
     * @param user
     */
    User addUser(User user);

    // All the methods of the usergroups

    List<UserGroup> getAllUserGroups(int ID);

    /**
     * get all the groups from a user
     *
     * @param userID
     * @return all usergroups of a user
     */
    List<UserGroup> getAllUserGroupsFromUser(int userID);

    /**
     * get all the users of a group
     *
     * @param groupID
     * @return all usergroups of a group
     */
    List<UserGroup> getAllUserGroupsFromGroup(int groupID);

    /**
     * adding a usergroup to the database
     *
     * @param userGroup
     * @return the added usergroup
     */
    UserGroup addUserGroup(UserGroup userGroup);

    // All the methods of the groups
    /**
     * Getting all groups
     *
     * @return list of groups
     */
    List<Group> getAllGroups();

    /**
     * Getting a specific group
     *
     * @param groupID
     * @return a user
     */
    Group getGroupFromId(int groupID);

    /**
     * Adding a group into the database
     *
     * @param group
     */
    Group addGroup(Group group);

    // All the method of the racers

    /**
     * This method return all the user from the database
     *
     * @return List of racers
     */
    List<Racer> getallRacers();

    /**
     * this method gets a racer base on its id
     *
     * @param racerID
     * @return racer
     */
    Racer getRacerFromId(int racerID);

    /**
     *  get a specific racer from a team
     *
     * @param teamID
     * @return
     */
    List<Racer> getTeamsFromId(int teamID);

    /**
     * get al the racers from a specific team
     *
     * @param teamID
     * @return
     */
    List<Racer> getRacersFromTeam(int teamID);

    /**
     * This method adds a racer to the database
     *
     * @return the added racer
     */
    Racer addRacer(Racer racer);

    // All the methods for the teams

    /**
     * get all the teams
     *
     * @return list of teams
     */
    List<Team> getAllTeams();

    /**
     * get a specific team
     *
     * @param teamID
     * @return specific team
     */
    Team getTeamFromId(int teamID);

    /**
     * adding a team to the database
     *
     * @param team
     * @return added team
     */
    Team addTeam(Team team);
}
