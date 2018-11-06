package com.aquadis.service.impl;

import com.aquadis.models.Group;
import com.aquadis.models.User;
import com.aquadis.service.RepositoryService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lorenzo
 */
public class RepositoryServiceImpl implements RepositoryService {

    private EntityManagerFactory entityManagerFactory;

    // A singleton reference
    private static RepositoryServiceImpl instance;

    // An instance of the service is created and during class initialisation
    static {
        instance = new RepositoryServiceImpl();
        instance.loadExamples();
    }

    //  Method to get a reference to the instance (singleton)
    public static RepositoryService getInstance() {
        return instance;
    }

    // An attribute that stores all users (in memory)
    private Map<Integer, User> elements;

    private RepositoryServiceImpl() {
        // TODO: database connection
        entityManagerFactory = Persistence.createEntityManagerFactory("aquadisPU");
    }

    private EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = getEntityManager();

        // TODO: get correct query
        List<User> users = entityManager.createQuery("SELECT u from User u").getResultList();

        entityManager.close();

        return users;
    }

    @Override
    public User getUserFromId(int userID) {
        EntityManager entityManager = getEntityManager();

        User user = entityManager.find(User.class, userID);

        entityManager.close();

        return user;
    }

    @Override
    public User addUser(User user) {
        EntityManager entityManager = getEntityManager();

        // Adds a user to the database
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();

        entityManager.close();

        return user;
    }

    @Override
    public List<Group> getGroupsFromUser(User user) {
        EntityManager entityManager = getEntityManager();

        // TODO: fix query
        Query query = entityManager.createQuery(""); // TODO: userID as paramater!!
        query.setParameter("userID", user.getId());

        List<Group> groups = query.getResultList();

        entityManager.close();

        return groups;
    }

    @Override
    public Group getGroupFromId(int userID, int groupID) {
        return elements.get(userID).getGroup(groupID);
    }

    @Override
    public Group addGroup(User user, Group group){
        user.addGroup(group);

        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(group);
        entityManager.getTransaction().commit();

        entityManager.close();

        return group;
    }

    private void loadExamples() {
        User luuk = new User(1, "luuk123@hotmail.nl", "luuk", "Luuk", "Goedhart", "luuk123");
        addUser(luuk);
        User lorenzo = new User(2, "lkorn.9520@gmail.com", "ALLIGAT0R_BL00D", "Lorenzo", "Korn", "lorenzo123");
        addUser(lorenzo);
        User janWillem = new User(3, "jwvbremen@hotmail.nl", "PhyrexAlianza", "JW", "van Bremen", "jw123");
        addUser(janWillem);
        User hugo = new User(4, "hugo123@outlook.nl", "hugo1997", "Hugo", "de Groot", "hugo123");
        addUser(hugo);

        Group group1 = new Group(1, "ALLIGAT0R_BL00D's group", lorenzo);
        group1.addUser(luuk);
        group1.addUser(janWillem);

        Group group2 = new Group(2, "luuk's group", luuk);
        group2.addUser(janWillem);

        Group group3 = new Group(3, "jw's group", janWillem);
        group3.addUser(lorenzo);
        group3.addUser(luuk);
    }
}
