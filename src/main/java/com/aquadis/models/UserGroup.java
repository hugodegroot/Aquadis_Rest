package com.aquadis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "User_Group")
public class UserGroup {

    @Transient
    @JsonIgnore
    private final int START_BUDGET = 20000000;

    @Id
    @Column(name = "UserGroup_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private int groupId;
    private int raceId;

    @Column(name = "points")
    private int points;

    @Column(name = "role")
    private String role;

    @Column(name = "budget")
    private int budget;

    private String firstName;

    private String lastName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "race_id")
    private Race race;

    private String raceName;

    public UserGroup() {
    }

    public UserGroup(int points, String role, User user, Group group, Race race) {
        setUserId(user.getId());
        setGroupId(group.getId());
        setRaceId(race.getId());
        setPoints(points);
        setRole(role);
        setBudget(START_BUDGET);
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setUser(user);
        setGroup(group);
        setRace(race);
        setRaceName(race.getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    private void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getRaceId() {
        return raceId;
    }

    private void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getPoints() {
        return points;
    }

    private void setPoints(int points) {
        this.points = points;
    }

    public String getRole() {
        return role;
    }

    private void setRole(String role) {
        this.role = role;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    private void setGroup(Group group) {
        this.group = group;
    }

    public Race getRace() {
        return race;
    }

    private void setRace(Race race) {
        this.race = race;
    }

    public String getRaceName() {
        return raceName;
    }

    private void setRaceName(String raceName) {
        this.raceName = raceName;
    }
}
