package com.aquadis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * @author Lorenzo
 */
@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private int isAdmin = 0;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserGroup> groups;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<UserGroup> races;

    @OneToMany(mappedBy = "userMax", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<MaxPrediction> maxPositionPredictions;

    public User() {
    }

    public User(String email, String firstName, String lastName, String password, int isAdmin) {
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setIsAdmin(isAdmin);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    private void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroup> groups) {
        this.groups = groups;
    }

    public List<UserGroup> getRaces() {
        return races;
    }

    public void setRaces(List<UserGroup> races) {
        this.races = races;
    }

    public List<MaxPrediction> getMaxPositionPredictions() {
        return maxPositionPredictions;
    }

    public void setMaxPositionPredictions(List<MaxPrediction> maxPositionPredictions) {
        this.maxPositionPredictions = maxPositionPredictions;
    }
}
