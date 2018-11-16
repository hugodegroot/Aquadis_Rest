package com.aquadis.service.impl;

import com.aquadis.models.*;
import com.aquadis.service.RepositoryService;

import javax.persistence.*;
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

        Query query = entityManager.createQuery("SELECT ug.user FROM UserGroup ug WHERE ug.group.id = :groupID");
        query.setParameter("groupID", groupID);

        List<UserGroup> userGroups = query.getResultList();

        entityManager.close();

        return userGroups;
    }

    @Override
    public User getUserFromloginFields(String username, String password){
        EntityManager entityManager = getEntityManager();

        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.userName = :username AND u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);


        User user = (User)query.getSingleResult();

        entityManager.close();
        return user;
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
    public List<Racer> getallRacers() {
        EntityManager entityManager = getEntityManager();

        List<Racer> racers = entityManager.createQuery("SELECT r FROM Racer r").getResultList();

        entityManager.close();

        return racers;
    }

    @Override
    public Racer getRacerFromId(int racerID) {
        EntityManager entityManager = getEntityManager();

        Racer racer = entityManager.find(Racer.class, racerID);

        entityManager.close();

        return racer;
    }

    @Override
    public List<Racer> getTeamsFromId(int teamID) {
        EntityManager entityManager = getEntityManager();

        List<Racer> teams = entityManager.createQuery("SELECT t FROM Racer r INNER JOIN r.team  t ON r.team.id = t.id").getResultList();

        entityManager.close();

        return teams;
    }

    @Override
    public List<Racer> getRacersFromTeam(int teamID) {
        EntityManager entityManager = getEntityManager();

        Query query = entityManager.createQuery("SELECT r FROM Racer r WHERE r.team.id = :teamID");
        query.setParameter("teamID",teamID);

        List<Racer> racers = query.getResultList();

        entityManager.close();

        return racers;
    }

    @Override
    public Racer addRacer(Racer racer) {
        return (Racer) addEntity(racer);
    }

    @Override
    public List<Team> getAllTeams() {
        EntityManager entityManager = getEntityManager();

        List<Team> teams = entityManager.createQuery("SELECT t FROM Team t").getResultList();

        entityManager.close();

        return teams;
    }

    @Override
    public Team getTeamFromId(int teamID) {
        EntityManager entityManager = getEntityManager();

        Team team = entityManager.find(Team.class, teamID);

        entityManager.close();

        return team;
    }

    @Override
    public Team addTeam(Team team) {
        return (Team) addEntity(team);
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

        // Adds the teams to the database
        Team mercedes = new Team("Mercedes");
        addTeam(mercedes);
        Team redbull = new Team("Redbull");
        addTeam(redbull);
        Team scuderiaFerrari = new Team("Scuderia ferrari");
        addTeam(scuderiaFerrari);
        Team forceIndiaF1Team = new Team("Force India F1 Team");
        addTeam(forceIndiaF1Team);
        Team renault = new Team("Renault");
        addTeam(renault);
        Team mcLaren = new Team("McLaren");
        addTeam(mcLaren);
        Team williams = new Team("Williams");
        addTeam(williams);
        Team scuderiaToroRosso = new Team("Scuderia Toro Rosso");
        addTeam(scuderiaToroRosso);
        Team haasF1Team = new Team("Haas F1 Team");
        addTeam(haasF1Team);
        Team sauber = new Team("Sauber");
        addTeam(sauber);

        // Adds twenty racers to the database
        Racer hamilton = new Racer("", "Hamilton", 7000000, mercedes);
        addRacer(hamilton);
        Racer bottas = new Racer("", "bottas", 6000000, mercedes);
        addRacer(bottas);
        Racer ricciardo = new Racer("", "ricciardo", 6000000, redbull);
        addRacer(ricciardo);
        Racer verstappen = new Racer("", "verstappen", 6000000, redbull);
        addRacer(verstappen);
        Racer vettel = new Racer("", "vettel", 7000000, scuderiaFerrari);
        addRacer(vettel);
        Racer raikkonen = new Racer("", "raikkonen", 6000000, scuderiaFerrari);
        addRacer(raikkonen);
        Racer perez = new Racer("", "perez", 3000000, forceIndiaF1Team);
        addRacer(perez);
        Racer ocon = new Racer("", "ocon", 3000000, forceIndiaF1Team);
        addRacer(ocon);
        Racer hulkenberg = new Racer("", "hülkenberg", 4000000, renault);
        addRacer(hulkenberg);
        Racer sainz = new Racer("", "sainz", 3000000, renault);
        addRacer(sainz);
        Racer vandoorne = new Racer("", "vandoorne", 3000000, mcLaren);
        addRacer(vandoorne);
        Racer alonzo = new Racer("", "alonzo", 4000000, mcLaren);
        addRacer(alonzo);
        Racer stroll = new Racer("", "stroll", 2000000, williams);
        addRacer(stroll);
        Racer sirotkin = new Racer("", "Sirotkin", 1000000, williams);
        addRacer(sirotkin);
        Racer gasly = new Racer("", "Gasly", 2000000, scuderiaToroRosso);
        addRacer(gasly);
        Racer hartley = new Racer("", "Hartley", 2000000, scuderiaToroRosso);
        addRacer(hartley);
        Racer grosjean = new Racer("", "Grosjean", 2000000, haasF1Team);
        addRacer(grosjean);
        Racer magnussen = new Racer("", "Magnussen", 3000000, haasF1Team);
        addRacer(magnussen);
        Racer ericsson = new Racer("", "Ericsson", 1000000, sauber);
        addRacer(ericsson);
        Racer leclerc = new Racer("", "Leclerc", 1000000, sauber);
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
