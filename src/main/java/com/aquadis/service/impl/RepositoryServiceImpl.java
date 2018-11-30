package com.aquadis.service.impl;

import com.aquadis.models.*;
import com.aquadis.service.RepositoryService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
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
//        instance.loadExamples();
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
    public List<UserGroup> getAllUserGroups(int ID) {
        EntityManager entityManager = getEntityManager();

        Query query = entityManager.createQuery("SELECT ug FROM UserGroup ug WHERE ug.user.id = :ID");
        query.setParameter("ID", ID);

        List<UserGroup> userGroups = query.getResultList();

        entityManager.close();

        return userGroups;
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
    public User getUserFromloginFields(String email, String password) {
        EntityManager entityManager = getEntityManager();

        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);


        User user = (User) query.getSingleResult();

        entityManager.close();

        if (user == null) {
            return null;
        }

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
    public List<Racer> getAllRacers() {
        EntityManager entityManager = getEntityManager();

        List<Racer> racers = entityManager.createQuery("SELECT r FROM Racer r ORDER BY r.salary DESC").getResultList();

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
        query.setParameter("teamID", teamID);

        List<Racer> racers = query.getResultList();

        entityManager.close();

        return racers;
    }

    @Override
    public Racer updateRacerSalary(int racerID, int salary) {
        EntityManager entityManager = getEntityManager();

        Racer racer = entityManager.find(Racer.class, racerID);

        entityManager.getTransaction().begin();
        racer.setSalary(salary);
        entityManager.getTransaction().commit();

        entityManager.close();

        return racer;
    }

    @Override
    public Racer addRacer(Racer racer) {
        return (Racer) addEntity(racer);
    }

    @Override
    public List<Team> getAllTeams() {
        EntityManager entityManager = getEntityManager();

        List<Team> teams = entityManager.createQuery("SELECT t FROM Team t ORDER BY t.name").getResultList();

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

    @Override
    public List<Race> getAllRaces() {
        LocalDate localDate = LocalDate.now();
        Date date = new Date(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());

        EntityManager entityManager = getEntityManager();

        Query query = entityManager.createQuery("SELECT r FROM Race r where r.endDate > :currentDate ORDER BY r.id");
        query.setParameter("currentDate", date, TemporalType.DATE);

        List<Race> races = query.getResultList();
        races.remove(0);

        entityManager.close();

        return races;
    }

    @Override
    public Race getCurrentRace() {
        LocalDate localDate = LocalDate.now();
        Date date = new Date(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());

        EntityManager entityManager = getEntityManager();

        Query query = entityManager.createQuery("SELECT r FROM Race r WHERE r.startDate >= :currentDate");
        query.setParameter("currentDate", date, TemporalType.DATE);

        Race race = (Race) query.getResultList().get(0);

        entityManager.close();

        return race;
    }

    @Override
    public Race getRaceFromId(int raceID) {
        EntityManager entityManager = getEntityManager();

        Race race = entityManager.find(Race.class, raceID);

        entityManager.close();

        return race;
    }

    @Override
    public List<RacePosition> getRacePositionsFromRace(int raceID) {
        EntityManager entityManager = getEntityManager();

        Query query = entityManager.createQuery("SELECT rp FROM RacePosition rp WHERE rp.race.id = :raceID");
        query.setParameter("raceID", raceID);

        List<RacePosition> racePositions = query.getResultList();

        entityManager.close();

        return racePositions;
    }

    @Override
    public Race addRace(Race race) {
        return (Race) addEntity(race);
    }

    @Override
    public RacePosition addRacePosition(RacePosition racePosition) {
        return (RacePosition) addEntity(racePosition);
    }

    @Override
    public Position addPosition(Position position) {
        return (Position) addEntity(position);
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
    }
}
