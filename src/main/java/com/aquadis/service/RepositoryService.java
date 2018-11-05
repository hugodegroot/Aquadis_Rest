package com.aquadis.service;

import com.aquadis.models.Group;
import com.aquadis.models.User;

import java.util.List;

/**
 * @author Lorenzo
 */
public interface RepositoryService {

    /**
     * Getting all users
     *
     * @return list of users
     */
    List<User> getAllUsers();

    /**
     * Getting a specific flash card
     *
     * @param userID
     * @return a user
     */
    User getUserFromId(int userID);

    /**
     * Adding a user
     *
     * @param user
     */
    User addUser(User user);

    /**
     * Getting all group of a user
     *
     * @return list of groups
     */
    List<Group> getGroupsFromUser(int userID);

    /**
     * Getting a specific group of a user
     *
     * @param userID
     * @return a group
     */
    Group getGroupFromId(int userID, int groupID);

    Group addGroup(User user, Group group);
}
