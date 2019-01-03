package com.aquadis.service.impl;

import com.aquadis.models.*;
import com.aquadis.service.RepositoryService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author Lorenzo
 */
public class RepositoryServiceImpl implements RepositoryService {

    private EntityManagerFactory entityManagerFactory;

    // A singleton reference
    private static RepositoryServiceImpl instance;

    // An instance of the service is created and during class initialisation
    static {
        System.out.println("Make new instance.");
        instance = new RepositoryServiceImpl();
        System.out.println("Load examples.");
        instance.loadExamples();
    }

    //  Method to get a reference to the instance (singleton)
    public static RepositoryService getInstance() {
        return instance;
    }

    private RepositoryServiceImpl() {
        System.out.println("Connect to database.");
        entityManagerFactory = Persistence.createEntityManagerFactory("aquadis_persistence_unit");
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public List<User> getAllUsers() {
        System.out.println("Before get all users.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Get all users create query.");
        List<User> users = entityManager.createQuery("SELECT u from User u").getResultList();

        entityManager.close();

        return users;
    }

    @Override
    public List<User> getUsersByEmailOrName(String email, String firstName, String lastName) {
        EntityManager entityManager = getEntityManager();

        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email like CONCAT('%',:email,'%') OR u.firstName like CONCAT('%',:firstName,'%') OR u.lastName like CONCAT('%',:lastName,'%')");
        query.setParameter("email", email);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);

        System.out.println("KAK!" + query.toString());

        List<User> users = query.getResultList();

        entityManager.close();

        return users;

    }

    @Override
    public User getUserFromId(int userID) {
        System.out.println("Before get user from id");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before find user.");
        User user = entityManager.find(User.class, userID);

        entityManager.close();

        return user;
    }

    @Override
    public User addUser(User user) {
        System.out.println("Before add user.");
        return (User) addEntity(user);
    }

    @Override
    public List<UserGroup> getAllUserGroups(int ID) {
        System.out.println("Before get all user groups.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get al user groups create query.");
        Query query = entityManager.createQuery("SELECT ug FROM UserGroup ug WHERE ug.user.id = :ID");
        query.setParameter("ID", ID);

        System.out.println("Before get result list.");
        List<UserGroup> userGroups = query.getResultList();

        entityManager.close();

        return userGroups;
    }

    @Override
    public List<UserGroup> getAllUserGroupsFromUser(int userID) {
        System.out.println("Before get all user groups from user.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get all user groups from user create query.");
        Query query = entityManager.createQuery("SELECT g FROM UserGroup ug INNER JOIN ug.group g WHERE ug.user.id = :userID");
        query.setParameter("userID", userID);

        System.out.println("Before get result list.");
        List<UserGroup> userGroups = query.getResultList();

        entityManager.close();

        return userGroups;
    }

    @Override
    public List<UserGroup> getAllUserGroupsFromGroup(int groupID) {
        System.out.println("Before get all user groups from group.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get all user groups from group create query.");
        Query query = entityManager.createQuery("SELECT ug.user FROM UserGroup ug WHERE ug.group.id = :groupID");
        query.setParameter("groupID", groupID);

        System.out.println("Before get result list.");
        List<UserGroup> userGroups = query.getResultList();

        entityManager.close();

        return userGroups;
    }

    @Override
    public User getUserFromLoginFields(String email, String password) {
        System.out.println("Before get user from login fields.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get user from login field create query.");
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        System.out.println("Before get result of query");
        User user = (User) query.getSingleResult();

        entityManager.close();

        if (user == null) {
            return null;
        }

        return user;
    }

    @Override
    public UserGroup addUserGroup(UserGroup userGroup) {
        System.out.println("Before add user group");
        return (UserGroup) addEntity(userGroup);
    }

    @Override
    public List<Group> getAllGroups() {
        System.out.println("Before get all groups");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get all groups create query");
        List<Group> groups = entityManager.createQuery("SELECT g from Group g").getResultList();

        entityManager.close();

        return groups;
    }

    @Override
    public Group getGroupFromId(int groupID) {
        System.out.println("Before get group from id.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before find group");
        Group group = entityManager.find(Group.class, groupID);

        entityManager.close();

        return group;
    }

    @Override
    public Group getLastAddedGroup() {
        EntityManager entityManager = getEntityManager();

        Group group = (Group)entityManager.createQuery("select g from Group g order by g.id desc ").setMaxResults(1).getSingleResult();
        System.out.println("group.getName() = " + group.getName());
        return group;
    }

    @Override
    public Group addGroup(Group group) {
        System.out.println("Before add group.");
        return (Group) addEntity(group);
    }

    @Override
    public List<Racer> getAllRacers() {
        System.out.println("Before get all racers.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get all racers create query.");
        List<Racer> racers = entityManager.createQuery("SELECT r FROM Racer r ORDER BY r.salary DESC").getResultList();

        entityManager.close();

        return racers;
    }

    @Override
    public Racer getRacerFromId(int racerID) {
        System.out.println("Before get racer from id.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get racer from id create query.");
        Racer racer = entityManager.find(Racer.class, racerID);

        entityManager.close();

        return racer;
    }

    @Override
    public List<Racer> getTeamsFromId(int teamID) {
        System.out.println("Begin get teams from id.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get team from id create query.");
        List<Racer> teams = entityManager.createQuery("SELECT t FROM Racer r INNER JOIN r.team  t ON r.team.id = t.id").getResultList();

        entityManager.close();

        return teams;
    }

    @Override
    public List<Racer> getRacersFromTeam(int teamID) {
        System.out.println("Before get racers from team.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get racers from team create query.");
        Query query = entityManager.createQuery("SELECT r FROM Racer r WHERE r.team.id = :teamID");
        query.setParameter("teamID", teamID);

        System.out.println("Before get result.");
        List<Racer> racers = query.getResultList();

        entityManager.close();

        return racers;
    }

    @Override
    public Racer updateRacerSalary(int racerID, int salary) {
        System.out.println("Before update racer salary.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before find racer.");
        Racer racer = entityManager.find(Racer.class, racerID);

        System.out.println("Before transaction.");
        entityManager.getTransaction().begin();
        racer.setSalary(salary);
        entityManager.getTransaction().commit();

        entityManager.close();

        return racer;
    }

    @Override
    public Racer addRacer(Racer racer) {
        System.out.println("Before add racer.");
        return (Racer) addEntity(racer);
    }

    @Override
    public List<Team> getAllTeams() {
        System.out.println("Before get all teams.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get all teams create query.");
        List<Team> teams = entityManager.createQuery("SELECT t FROM Team t ORDER BY t.name").getResultList();

        entityManager.close();

        return teams;
    }

    @Override
    public Team getTeamFromId(int teamID) {
        System.out.println("Before get team from id.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before find team.");
        Team team = entityManager.find(Team.class, teamID);

        entityManager.close();

        return team;
    }

    @Override
    public Team addTeam(Team team) {
        System.out.println("Before add team.");
        return (Team) addEntity(team);
    }

    @Override
    public List<Race> getAllRaces() {
        LocalDate localDate = LocalDate.now();
        Date date = new Date(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());

        System.out.println("Before get all races.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get all races create query.");
        Query query = entityManager.createQuery("SELECT r FROM Race r where r.endDate > :currentDate ORDER BY r.id");
        query.setParameter("currentDate", date, TemporalType.DATE);

        System.out.println("Before get result");
        List<Race> races = query.getResultList();
        races.remove(0);

        entityManager.close();

        return races;
    }

    @Override
    public Race getCurrentRace() {
        LocalDate localDate = LocalDate.now();
        Date date = new Date(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());

        System.out.println("Before get current race.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get current race create query.");
        Query query = entityManager.createQuery("SELECT r FROM Race r WHERE r.startDate >= :currentDate");
        query.setParameter("currentDate", date, TemporalType.DATE);

        System.out.println("Before get result");
        Race race = (Race) query.getResultList().get(0);

        entityManager.close();

        return race;
    }

    @Override
    public Race getRaceFromId(int raceID) {
        System.out.println("Before get race from id.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before find race.");
        Race race = entityManager.find(Race.class, raceID);

        entityManager.close();

        return race;
    }

    @Override
    public List<RacePosition> getRacePositionsFromRace(int raceID) {
        System.out.println("Before get race positions from race.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before get race positions from race create query.");
        Query query = entityManager.createQuery("SELECT rp FROM RacePosition rp WHERE rp.race.id = :raceID");
        query.setParameter("raceID", raceID);

        System.out.println("Before get result.");
        List<RacePosition> racePositions = query.getResultList();

        entityManager.close();

        return racePositions;
    }

    @Override
    public Race addRace(Race race) {
        System.out.println("Before add race.");
        return (Race) addEntity(race);
    }

    @Override
    public RacePosition addRacePosition(RacePosition racePosition) {
        System.out.println("Before add race position.");
        return (RacePosition) addEntity(racePosition);
    }

    @Override
    public Position getPositionFromId(int positionId) {
        System.out.println("Before get position from id.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before find position.");
        Position position = entityManager.find(Position.class, positionId);

        entityManager.close();

        return position;
    }

    @Override
    public Position addPosition(Position position) {
        System.out.println("Before add position.");
        return (Position) addEntity(position);
    }

    @Override
    public List<MaxPrediction> getAllMaxPredictions() {
        EntityManager entityManager = getEntityManager();

        List<MaxPrediction> maxPredictions = entityManager.createQuery("SELECT mp FROM MaxPrediction mp", MaxPrediction.class).getResultList();

        entityManager.close();

        return maxPredictions;
    }

    @Override
    public MaxPrediction getMaxPredictionsFromId(int userId, int raceId) {
        EntityManager entityManager = getEntityManager();

        Query query = entityManager.createQuery("SELECT mp FROM MaxPrediction mp WHERE mp.userMax.id = :userId AND mp.raceMax.id= :raceId");
        query.setParameter("userId", userId);
        query.setParameter("raceId", raceId);

        MaxPrediction maxPrediction = (MaxPrediction) query.getSingleResult();
        entityManager.close();
        return maxPrediction;
    }

    @Override
    public MaxPrediction addMaxPrediction(MaxPrediction maxPrediction) {
        return (MaxPrediction) addEntity(maxPrediction);
    }

    /**
     * Adds a object to the database. In our case the object
     *
     * @param object User or Group
     * @return the object
     */
    private Object addEntity(Object object) {
        System.out.println("Before add entity.");
        EntityManager entityManager = getEntityManager();

        System.out.println("Before transaction.");
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();

        entityManager.close();

        return object;
    }

    private void loadExamples() {
        String nothing = "";
        String dnf = "DNF";
        String dq = "DQ";


        // Adds four users to the database
        User luuk = new User("luuk123@hotmail.nl", "Luuk", "Goedhart", "luuk123", 1);
        addUser(luuk);
        User lorenzo = new User("lkorn.9520@gmail.com", "Lorenzo", "Korn", "lorenzo123", 1);
        addUser(lorenzo);
        User janWillem = new User("jwvbremen@hotmail.nl", "Jan-Willem", "van Bremen", "jw123", 0);
        addUser(janWillem);
        User hugo = new User("hugo123@outlook.nl", "Hugo", "de Groot", "hugo123", 0);
        addUser(hugo);
        User john = new User("johnDough@outlook.nl", "John", "Dough", "jd123", 1);
        addUser(john);

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
        Racer hamilton = new Racer("Lewis", "Hamilton", 7000000, mercedes);
        addRacer(hamilton);
        Racer bottas = new Racer("Valtteri", "bottas", 6000000, mercedes);
        addRacer(bottas);
        Racer ricciardo = new Racer("Daniel", "ricciardo", 6000000, redbull);
        addRacer(ricciardo);
        Racer verstappen = new Racer("Max", "verstappen", 6000000, redbull);
        addRacer(verstappen);
        Racer vettel = new Racer("Sebastian", "vettel", 7000000, scuderiaFerrari);
        addRacer(vettel);
        Racer raikkonen = new Racer("Kimi", "raikkonen", 6000000, scuderiaFerrari);
        addRacer(raikkonen);
        Racer perez = new Racer("Kenneth", "perez", 3000000, forceIndiaF1Team);
        addRacer(perez);
        Racer ocon = new Racer("Esteban", "ocon", 3000000, forceIndiaF1Team);
        addRacer(ocon);
        Racer hulkenberg = new Racer("Nico", "hülkenberg", 4000000, renault);
        addRacer(hulkenberg);
        Racer sainz = new Racer("Carlos", "sainz", 3000000, renault);
        addRacer(sainz);
        Racer vandoorne = new Racer("Stoffel", "vandoorne", 3000000, mcLaren);
        addRacer(vandoorne);
        Racer alonzo = new Racer("Fernando", "alonzo", 4000000, mcLaren);
        addRacer(alonzo);
        Racer stroll = new Racer("Lance", "stroll", 2000000, williams);
        addRacer(stroll);
        Racer sirotkin = new Racer("Sergej", "Sirotkin", 1000000, williams);
        addRacer(sirotkin);
        Racer gasly = new Racer("Pierre", "Gasly", 2000000, scuderiaToroRosso);
        addRacer(gasly);
        Racer hartley = new Racer("Brandon", "Hartley", 2000000, scuderiaToroRosso);
        addRacer(hartley);
        Racer grosjean = new Racer("Romain", "Grosjean", 2000000, haasF1Team);
        addRacer(grosjean);
        Racer magnussen = new Racer("Kevin", "Magnussen", 3000000, haasF1Team);
        addRacer(magnussen);
        Racer ericsson = new Racer("Marcus", "Ericsson", 1000000, sauber);
        addRacer(ericsson);
        Racer leclerc = new Racer("Charles", "Leclerc", 1000000, sauber);
        addRacer(leclerc);

        // Adds three groups to the database
        Group group1 = new Group("Lorenzo\'s group");
        addGroup(group1);
        Group group2 = new Group("School");
        addGroup(group2);
        Group group3 = new Group("Work");
        addGroup(group3);

        // TODO: Adds races
        Race usa = new Race("FORMULA 1 PIRELLI 2018 UNITED STATES GRAND PRIX", "USA", new Date(2018, 9, 19), new Date(2018, 9, 21));
        addRace(usa);
        Race mexico = new Race("FORMULA 1 GRAN PREMIO DE MÉXICO 2018", "Mexico", new Date(2018, 9, 26), new Date(2018, 9, 28));
        addRace(mexico);
        Race brazil = new Race("FORMULA 1 GRANDE PRÊMIO HEINEKEN DO BRASIL 2018", "Brazil", new Date(2018, 10, 9), new Date(2018, 10, 11));
        addRace(brazil);
        Race abuDhabi = new Race("FORMULA 1 2018 ETIHAD AIRWAYS ABU DHABI GRAND PRIX", "Abu Dhabi", new Date(2018, 10, 23), new Date(2018, 10, 25));
        addRace(abuDhabi);
        Race australia = new Race("FORMULA 1 2019 Australia Melbourne GRAND PRIX", "Melbourne", new Date(2019, 2, 17), new Date(2019, 2, 19));
        addRace(australia);
        Race bahrain = new Race("FORMULA 1 2019 Bahrain Sakhir GRAND PRIX", "Sakhir", new Date(2019, 2, 31), new Date(2019, 3, 2));
        addRace(bahrain);
        Race china = new Race("FORMULA 1 2019 China Shanghai GRAND PRIX", "Shanghai", new Date(2019, 3, 14), new Date(2019, 3, 16));
        addRace(china);

        // Adds usergroups
        UserGroup usergroup1 = new UserGroup(13, "admin", lorenzo, group1, abuDhabi);
        usergroup1.setBudget(9000000);
        addUserGroup(usergroup1);
        UserGroup usergroup2 = new UserGroup(5, "member", luuk, group1, abuDhabi);
        usergroup2.setBudget(2000000);
        addUserGroup(usergroup2);
        UserGroup usergroup3 = new UserGroup(8, "member", janWillem, group1, abuDhabi);
        usergroup3.setBudget(4000000);
        addUserGroup(usergroup3);
        UserGroup usergroup4 = new UserGroup(19, "admin", luuk, group2, abuDhabi);
        usergroup4.setBudget(8000000);
        addUserGroup(usergroup4);
        UserGroup usergroup5 = new UserGroup(11, "member", lorenzo, group2, abuDhabi);
        usergroup5.setBudget(9000000);
        addUserGroup(usergroup5);
        UserGroup usergroup6 = new UserGroup(4, "member", hugo, group2, abuDhabi);
        usergroup6.setBudget(11000000);
        addUserGroup(usergroup6);
        UserGroup usergroup7 = new UserGroup(10, "admin", janWillem, group3, abuDhabi);
        usergroup7.setBudget(14000000);
        addUserGroup(usergroup7);
        UserGroup usergroup8 = new UserGroup(1, "member", lorenzo, group3, abuDhabi);
        usergroup8.setBudget(15000000);
        addUserGroup(usergroup8);
        UserGroup usergroup9 = new UserGroup(0, "member", luuk, group3, abuDhabi);
        usergroup9.setBudget(6000000);
        addUserGroup(usergroup9);
        UserGroup usergroup10 = new UserGroup(0, "member", hugo, group3, abuDhabi);
        usergroup10.setBudget(2000000);
        addUserGroup(usergroup10);
        usergroup1 = new UserGroup(15, "admin", lorenzo, group1, australia);
        usergroup1.setBudget(0);
        addUserGroup(usergroup1);
        usergroup2 = new UserGroup(8, "member", luuk, group1, australia);
        usergroup2.setBudget(4000000);
        addUserGroup(usergroup2);
        usergroup3 = new UserGroup(1, "member", janWillem, group1, australia);
        usergroup3.setBudget(5000000);
        addUserGroup(usergroup3);
        usergroup4 = new UserGroup(9, "admin", luuk, group2, australia);
        usergroup4.setBudget(8000000);
        addUserGroup(usergroup4);
        usergroup5 = new UserGroup(21, "member", lorenzo, group2, australia);
        usergroup5.setBudget(9000000);
        addUserGroup(usergroup5);
        usergroup6 = new UserGroup(7, "member", hugo, group2, australia);
        usergroup6.setBudget(9000000);
        addUserGroup(usergroup6);
        usergroup7 = new UserGroup(53, "admin", janWillem, group3, australia);
        usergroup7.setBudget(7000000);
        addUserGroup(usergroup7);
        usergroup8 = new UserGroup(2, "member", lorenzo, group3, australia);
        usergroup8.setBudget(9000000);
        addUserGroup(usergroup8);
        usergroup9 = new UserGroup(18, "member", luuk, group3, australia);
        usergroup9.setBudget(1000000);
        addUserGroup(usergroup9);
        usergroup10 = new UserGroup(13, "member", hugo, group3, australia);
        usergroup10.setBudget(0);
        addUserGroup(usergroup10);
        usergroup1 = new UserGroup(1, "admin", lorenzo, group1, mexico);
        usergroup1.setBudget(10000000);
        addUserGroup(usergroup1);
        usergroup2 = new UserGroup(9, "member", luuk, group1, mexico);
        usergroup2.setBudget(12000000);
        addUserGroup(usergroup2);
        usergroup3 = new UserGroup(25, "member", janWillem, group1, mexico);
        usergroup3.setBudget(0);
        addUserGroup(usergroup3);
        usergroup4 = new UserGroup(53, "admin", luuk, group2, mexico);
        usergroup4.setBudget(13000000);
        addUserGroup(usergroup4);
        usergroup5 = new UserGroup(7, "member", lorenzo, group2, mexico);
        usergroup5.setBudget(2000000);
        addUserGroup(usergroup5);
        usergroup6 = new UserGroup(3, "member", hugo, group2, mexico);
        usergroup6.setBudget(5000000);
        addUserGroup(usergroup6);
        usergroup7 = new UserGroup(18, "admin", janWillem, group3, mexico);
        usergroup7.setBudget(0);
        addUserGroup(usergroup7);
        usergroup8 = new UserGroup(14, "member", lorenzo, group3, mexico);
        usergroup8.setBudget(11000000);
        addUserGroup(usergroup8);
        usergroup9 = new UserGroup(34, "member", luuk, group3, mexico);
        usergroup9.setBudget(0);
        addUserGroup(usergroup9);
        usergroup10 = new UserGroup(23, "member", hugo, group3, mexico);
        usergroup10.setBudget(10000000);
        addUserGroup(usergroup10);
        usergroup1 = new UserGroup(44, "admin", lorenzo, group1, brazil);
        usergroup1.setBudget(8000000);
        addUserGroup(usergroup1);
        usergroup2 = new UserGroup(5, "member", luuk, group1, brazil);
        usergroup2.setBudget(0);
        addUserGroup(usergroup2);
        usergroup3 = new UserGroup(6, "member", janWillem, group1, brazil);
        usergroup3.setBudget(3000000);
        addUserGroup(usergroup3);
        usergroup4 = new UserGroup(20, "admin", luuk, group2, brazil);
        usergroup4.setBudget(0);
        addUserGroup(usergroup4);
        usergroup5 = new UserGroup(7, "member", lorenzo, group2, brazil);
        usergroup5.setBudget(7000000);
        addUserGroup(usergroup5);
        usergroup6 = new UserGroup(19, "member", hugo, group2, brazil);
        usergroup6.setBudget(5000000);
        addUserGroup(usergroup6);
        usergroup7 = new UserGroup(34, "admin", janWillem, group3, brazil);
        usergroup7.setBudget(1000000);
        addUserGroup(usergroup7);
        usergroup8 = new UserGroup(19, "member", lorenzo, group3, brazil);
        usergroup8.setBudget(2000000);
        addUserGroup(usergroup8);
        usergroup9 = new UserGroup(5, "member", luuk, group3, brazil);
        usergroup9.setBudget(0);
        addUserGroup(usergroup9);
        usergroup10 = new UserGroup(1, "member", hugo, group3, brazil);
        usergroup10.setBudget(9000000);
        addUserGroup(usergroup10);

        // TODO: Adds positions
        Position first = new Position("First");
        addPosition(first);
        Position second = new Position("Second");
        addPosition(second);
        Position third = new Position("Third");
        addPosition(third);
        Position fourth = new Position("fourth");
        addPosition(fourth);
        Position fifth = new Position("fifth");
        addPosition(fifth);
        Position sixth = new Position("Sixth");
        addPosition(sixth);
        Position seventh = new Position("Seventh");
        addPosition(seventh);
        Position eighth = new Position("Eighth");
        addPosition(eighth);
        Position ninth = new Position("Ninth");
        addPosition(ninth);
        Position tenth = new Position("Tenth");
        addPosition(tenth);
        Position eleventh = new Position("Eleventh");
        addPosition(eleventh);
        Position twelfth = new Position("Twelfth");
        addPosition(twelfth);
        Position thirteenth = new Position("Thirteenth");
        addPosition(thirteenth);
        Position fourteenth = new Position("Fourteenth");
        addPosition(fourteenth);
        Position fifteenth = new Position("Fifteenth");
        addPosition(fifteenth);
        Position sixteenth = new Position("Sixteenth");
        addPosition(sixteenth);
        Position seventeenth = new Position("Seventeenth");
        addPosition(seventeenth);
        Position eighteenth = new Position("Eighteenth");
        addPosition(eighteenth);
        Position nineteenth = new Position("Nineteenth");
        addPosition(nineteenth);
        Position twentieth = new Position("Twentieth");
        addPosition(twentieth);
        Position none = new Position("");
        addPosition(none);

        // TODO: Adds racepositions
        RacePosition usaP1 = new RacePosition(usa, raikkonen, second, 1, 32, 237, first, 1, 34, 18, 643, 0, nothing);
        addRacePosition(usaP1);
        RacePosition usaP2 = new RacePosition(usa, verstappen, eighteenth, 1, 34, 766, second, 1, 34, 19, 924, 0, nothing);
        addRacePosition(usaP2);
        RacePosition usaP3 = new RacePosition(usa, hamilton, first, 1, 32, 237, third, 1, 34, 20, 985, 0, nothing);
        addRacePosition(usaP3);
        RacePosition usaP4 = new RacePosition(usa, vettel, fifth, 1, 32, 298, fourth, 1, 34, 36, 865, 0, nothing);
        addRacePosition(usaP4);
        RacePosition usaP5 = new RacePosition(usa, bottas, third, 1, 32, 616, fifth, 1, 34, 43, 387, 0, nothing);
        addRacePosition(usaP5);
        RacePosition usaP6 = new RacePosition(usa, hulkenberg, seventh, 1, 34, 215, sixth, 1, 35, 45, 853, 0, nothing);
        addRacePosition(usaP6);
        RacePosition usaP7 = new RacePosition(usa, sainz, eleventh, 1, 34, 566, seventh, 1, 35, 53, 637, 0, nothing);
        addRacePosition(usaP7);
        RacePosition usaP8 = new RacePosition(usa, perez, tenth, 1, 32, 594, eighth, 1, 35, 59, 723, 0, nothing);
        addRacePosition(usaP8);
        RacePosition usaP9 = new RacePosition(usa, hartley, twentieth, 0, 0, 0, ninth, 1, 34, 49, 995, 1, nothing);
        addRacePosition(usaP9);
        RacePosition usaP10 = new RacePosition(usa, ericsson, sixteenth, 1, 35, 536, tenth, 1, 34, 51, 284, 1, nothing);
        addRacePosition(usaP10);
        RacePosition usaP11 = new RacePosition(usa, vandoorne, seventeenth, 1, 35, 736, eleventh, 1, 34, 52, 667, 1, nothing);
        addRacePosition(usaP11);
        RacePosition usaP12 = new RacePosition(usa, gasly, nineteenth, 0, 0, 0, twelfth, 1, 35, 15, 821, 1, nothing);
        addRacePosition(usaP12);
        RacePosition usaP13 = new RacePosition(usa, sirotkin, fourteenth, 1, 35, 362, thirteenth, 1, 35, 28, 139, 1, nothing);
        addRacePosition(usaP13);
        RacePosition usaP14 = new RacePosition(usa, stroll, fifteenth, 1, 35, 480, fourteenth, 1, 34, 40, 494, 2, nothing);
        addRacePosition(usaP14);
        RacePosition usaP15 = new RacePosition(usa, leclerc, ninth, 1, 34, 420, none, 0, 34, 18, 643, 25, dnf);
        addRacePosition(usaP15);
        RacePosition usaP16 = new RacePosition(usa, ricciardo, fourth, 1, 33, 494, none, 0, 13, 32, 870, 48, dnf);
        addRacePosition(usaP16);
        RacePosition usaP17 = new RacePosition(usa, grosjean, eighth, 1, 34, 250, none, 0, 4, 39, 025, 54, dnf);
        addRacePosition(usaP17);
        RacePosition usaP18 = new RacePosition(usa, alonzo, thirteenth, 1, 35, 394, none, 0, 2, 13, 811, 55, dnf);
        addRacePosition(usaP18);
        RacePosition usaP19 = new RacePosition(usa, ocon, sixth, 1, 34, 145, none, 1, 35, 57, 931, 0, dq);
        addRacePosition(usaP19);
        RacePosition usaP20 = new RacePosition(usa, magnussen, twelfth, 1, 34, 732, none, 1, 35, 59, 300, 0, dq);
        addRacePosition(usaP20);

        RacePosition abudhabi1 = new RacePosition(usa, raikkonen, second, 1, 32, 237, first, 1, 34, 18, 643, 0, nothing);
        addRacePosition(abudhabi1);
        RacePosition abudhabi2 = new RacePosition(usa, verstappen, eighteenth, 1, 34, 766, second, 1, 34, 19, 924, 0, nothing);
        addRacePosition(abudhabi2);
        RacePosition abudhabi3 = new RacePosition(usa, hamilton, first, 1, 32, 237, third, 1, 34, 20, 985, 0, nothing);
        addRacePosition(abudhabi3);
        RacePosition abudhabi4 = new RacePosition(usa, vettel, fifth, 1, 32, 298, fourth, 1, 34, 36, 865, 0, nothing);
        addRacePosition(abudhabi4);
        RacePosition abudhabi5 = new RacePosition(usa, bottas, third, 1, 32, 616, fifth, 1, 34, 43, 387, 0, nothing);
        addRacePosition(abudhabi5);
        RacePosition abudhabi6 = new RacePosition(usa, hulkenberg, seventh, 1, 34, 215, sixth, 1, 35, 45, 853, 0, nothing);
        addRacePosition(abudhabi6);
        RacePosition abudhabi7 = new RacePosition(usa, sainz, eleventh, 1, 34, 566, seventh, 1, 35, 53, 637, 0, nothing);
        addRacePosition(abudhabi7);
        RacePosition abudhabi8 = new RacePosition(usa, perez, tenth, 1, 32, 594, eighth, 1, 35, 59, 723, 0, nothing);
        addRacePosition(abudhabi8);
        RacePosition abudhabi9 = new RacePosition(usa, hartley, twentieth, 0, 0, 0, ninth, 1, 34, 49, 995, 1, nothing);
        addRacePosition(abudhabi9);
        RacePosition abudhabi10 = new RacePosition(usa, ericsson, sixteenth, 1, 35, 536, tenth, 1, 34, 51, 284, 1, nothing);
        addRacePosition(abudhabi10);
        RacePosition abudhabi11 = new RacePosition(usa, vandoorne, seventeenth, 1, 35, 736, eleventh, 1, 34, 52, 667, 1, nothing);
        addRacePosition(abudhabi11);
        RacePosition abudhabi12 = new RacePosition(usa, gasly, nineteenth, 0, 0, 0, twelfth, 1, 35, 15, 821, 1, nothing);
        addRacePosition(abudhabi12);
        RacePosition abudhabi13 = new RacePosition(usa, sirotkin, fourteenth, 1, 35, 362, thirteenth, 1, 35, 28, 139, 1, nothing);
        addRacePosition(abudhabi13);
        RacePosition abudhabi14 = new RacePosition(usa, stroll, fifteenth, 1, 35, 480, fourteenth, 1, 34, 40, 494, 2, nothing);
        addRacePosition(abudhabi14);
        RacePosition abudhabi15 = new RacePosition(usa, leclerc, ninth, 1, 34, 420, none, 0, 34, 18, 643, 25, dnf);
        addRacePosition(abudhabi15);
        RacePosition abudhabi16 = new RacePosition(usa, ricciardo, fourth, 1, 33, 494, none, 0, 13, 32, 870, 48, dnf);
        addRacePosition(abudhabi16);
        RacePosition abudhabi17 = new RacePosition(usa, grosjean, eighth, 1, 34, 250, none, 0, 4, 39, 025, 54, dnf);
        addRacePosition(abudhabi17);
        RacePosition abudhabi18 = new RacePosition(usa, alonzo, thirteenth, 1, 35, 394, none, 0, 2, 13, 811, 55, dnf);
        addRacePosition(abudhabi18);
        RacePosition abudhabi19 = new RacePosition(usa, ocon, sixth, 1, 34, 145, none, 1, 35, 57, 931, 0, dq);
        addRacePosition(abudhabi19);
        RacePosition abudhabi20 = new RacePosition(usa, magnussen, twelfth, 1, 34, 732, none, 1, 35, 59, 300, 0, dq);
        addRacePosition(abudhabi20);

//        MaxPrediction mp1 = new MaxPrediction(luuk, abuDhabi, second);
//        addMaxPrediction(mp1);
//        MaxPrediction mp2 = new MaxPrediction(lorenzo, abuDhabi, first);
//        addMaxPrediction(mp2);
//        MaxPrediction mp3 = new MaxPrediction(janWillem, abuDhabi, tenth);
//        addMaxPrediction(mp3);
//        MaxPrediction mp4 = new MaxPrediction(hugo, abuDhabi, fifteenth);
//        addMaxPrediction(mp4);
    }
}
