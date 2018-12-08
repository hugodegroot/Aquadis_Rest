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


    public UserGroup() {
    }

    public UserGroup(int points, String role, User user, Group group) {
        setPoints(points);
        setRole(role);
        setBudget(START_BUDGET);
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setUser(user);
        setGroup(group);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    private void setBudget(int budget) {
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
}
