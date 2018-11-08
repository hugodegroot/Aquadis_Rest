package com.aquadis.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lorenzo
 */
@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue // auto-increment
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String userName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private int isAdmin = 0;

    // TODO: check check
    @Transient
    private List<Group> groups;

    public User() {
    }

    public User(String email, String userName, String firstName, String lastName, String password, int isAdmin) {
        setEmail(email);
        setUserName(userName);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setAdminStatus(isAdmin);
        setGroups(new ArrayList<Group>());
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
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

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getAdminStatus() {
        return isAdmin;
    }

    public void setAdminStatus(int status) {
        this.isAdmin = status;
    }

    public Group getGroup(int groupID) {
        for (Group group : groups) {
            if (group.getId() == groupID) {
                return group;
            }
        }

        return null;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public List<Group> getGroups() {
        return groups;
    }

    private void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
