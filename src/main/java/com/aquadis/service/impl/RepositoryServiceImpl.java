package com.aquadis.service.impl;

import com.aquadis.models.Group;
import com.aquadis.models.Racer;
import com.aquadis.models.User;
import com.aquadis.models.UserGroup;
import com.aquadis.service.RepositoryService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
        entityManagerFactory = Persistence.createEntityManagerFactory("aquadisPU");
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = getEntityManager();

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
        return (User) addEntity(user);
    }

    @Override
    public List<UserGroup> getAllUserGroupsFromUser(int userID) {
        EntityManager entityManager = getEntityManager();

        Query query = entityManager.createQuery("SELECT g FROM UserGroup ug INNER JOIN ug.group g WHERE ug.user.id = :userID");
        query.setParameter("userID", userID);

        List<UserGroup> userGroups = query.getResultList();

        entityManager.close();

        return userGroups;
    }

    @Override
    public List<UserGroup> getAllUserGroupsFromGroup(int groupID) {
        EntityManager entityManager = getEntityManager();

        Query query = entityManager.createQuery("SELECT u FROM UserGroup ug INNER JOIN ug.user u WHERE ug.group.id = :groupID");
        query.setParameter("groupID", groupID);

        List<UserGroup> userGroups = query.getResultList();

        entityManager.close();

        return userGroups;
    }

    @Override
    public UserGroup addUserGroup(UserGroup userGroup) {
        return (UserGroup) addEntity(userGroup);
    }

    @Override
    public List<Group> getAllGroups() {
        EntityManager entityManager = getEntityManager();

        List<Group> groups = entityManager.createQuery("SELECT g from Group g").getResultList();

        entityManager.close();

        return groups;
    }

    @Override
    public Group getGroupFromId(int groupID) {
        EntityManager entityManager = getEntityManager();

        Group group = entityManager.find(Group.class, groupID);

        entityManager.close();

        return group;
    }

    @Override
    public Group addGroup(Group group) {
        return (Group) addEntity(group);
    }

    @Override
    public Racer addRacer(Racer racer) {
        return (Racer) addEntity(racer);
    }

    /**
     * Adds a object to the database. In our case the object will be a user or a group
     *
     * @param object User or Group
     * @return the object
     */
    private Object addEntity(Object object) {
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();

        entityManager.close();

        return object;
    }

    private void loadExamples() {
        // Adds four users to the database
        User luuk = new User("luuk123@hotmail.nl", "luuk", "Luuk", "Goedhart", "luuk123", 1);
        addUser(luuk);
        User lorenzo = new User("lkorn.9520@gmail.com", "ALLIGAT0R_BL00D", "Lorenzo", "Korn", "lorenzo123", 1);
        addUser(lorenzo);
        User janWillem = new User("jwvbremen@hotmail.nl", "PhyrexAlianza", "JW", "van Bremen", "jw123", 0);
        addUser(janWillem);
        User hugo = new User("hugo123@outlook.nl", "hugo1997", "Hugo", "de Groot", "hugo123", 0);
        addUser(hugo);

        // Adds twenty racers to the database
        Racer hamilton = new Racer("", "Hamilton", 7000000);
        addRacer(hamilton);
        Racer bottas = new Racer("", "bottas", 6000000);
        addRacer(bottas);
        Racer ricciardo = new Racer("", "ricciardo", 6000000);
        addRacer(ricciardo);
        Racer verstappen = new Racer("", "verstappen", 6000000);
        addRacer(verstappen);
        Racer vettel = new Racer("", "vettel", 7000000);
        addRacer(vettel);
        Racer raikkonen = new Racer("", "raikkonen", 6000000);
        addRacer(raikkonen);
        Racer perez = new Racer("", "perez", 3000000);
        addRacer(perez);
        Racer ocon = new Racer("", "ocon", 3000000);
        addRacer(ocon);
        Racer huikenberg = new Racer("", "huikenberg", 4000000);
        addRacer(huikenberg);
        Racer sainz = new Racer("", "sainz", 3000000);
        addRacer(sainz);
        Racer vandoorne = new Racer("", "vandoorne", 3000000);
        addRacer(vandoorne);
        Racer alonzo = new Racer("", "alonzo", 4000000);
        addRacer(alonzo);
        Racer stroll = new Racer("", "stroll", 2000000);
        addRacer(stroll);
        Racer sirotkin = new Racer("", "Sirotkin", 1000000);
        addRacer(sirotkin);
        Racer gasly = new Racer("", "Gasly", 2000000);
        addRacer(gasly);
        Racer hartley = new Racer("", "Hartley", 2000000);
        addRacer(hartley);
        Racer grosjean = new Racer("", "Grosjean", 2000000);
        addRacer(grosjean);
        Racer magnussen = new Racer("", "Magnussen", 3000000);
        addRacer(magnussen);
        Racer ericsson = new Racer("", "Ericsson", 1000000);
        addRacer(ericsson);
        Racer leclerc = new Racer("", "Leclerc", 1000000);
        addRacer(leclerc);

        // Adds three groups to the database
        Group group1 = new Group("ALLIGAT0R_BL00Ds group");
        addGroup(group1);
        Group group2 = new Group("School");
        addGroup(group2);
        Group group3 = new Group("Work");
        addGroup(group3);

        // Adds usergroups
        UserGroup usergroup1 = new UserGroup(13, "admin", lorenzo, group1);
        addUserGroup(usergroup1);
        UserGroup usergroup2 = new UserGroup(5, "member", luuk, group1);
        addUserGroup(usergroup2);
        UserGroup usergroup3 = new UserGroup(8, "member", janWillem, group1);
        addUserGroup(usergroup3);
        UserGroup usergroup4 = new UserGroup(19, "admin", luuk, group2);
        addUserGroup(usergroup4);
        UserGroup usergroup5 = new UserGroup(11, "member", lorenzo, group2);
        addUserGroup(usergroup5);
        UserGroup usergroup6 = new UserGroup(4, "member", hugo, group2);
        addUserGroup(usergroup6);
        UserGroup usergroup7 = new UserGroup(10, "admin", janWillem, group3);
        addUserGroup(usergroup7);
        UserGroup usergroup8 = new UserGroup(1, "member", lorenzo, group3);
        addUserGroup(usergroup8);
        UserGroup usergroup9 = new UserGroup(0, "member", luuk, group3);
        addUserGroup(usergroup9);
        UserGroup usergroup10 = new UserGroup(0, "member", hugo, group3);
        addUserGroup(usergroup10);
    }
}
