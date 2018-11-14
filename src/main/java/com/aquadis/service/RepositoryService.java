package com.aquadis.service;

import com.aquadis.models.Group;
import com.aquadis.models.Racer;
import com.aquadis.models.User;
import com.aquadis.models.UserGroup;

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
     * Adding a user into the database
     *
     * @param user
     */
    User addUser(User user);

    // All the methods of the usergroups
    /**
     *
     * @param userID
     * @return all usergroups of a user
     */
    List<UserGroup> getAllUserGroupsFromUser(int userID);

    /**
     *
     * @param groupID
     * @return all usergroups of a group
     */
    List<UserGroup> getAllUserGroupsFromGroup(int groupID);

    /**
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
     * This method adds a racer to the database
     *
     * @return the added racer
     */
    Racer addRacer(Racer racer);
}
