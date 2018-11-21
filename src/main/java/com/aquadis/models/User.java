package com.aquadis.models;

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
    private List<UserGroup> groups;

    public User() {
    }

    public User(String email, String firstName, String lastName, String password, int isAdmin) {
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setAdminStatus(isAdmin);
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

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getAdminStatus() {
        return isAdmin;
    }

    private void setAdminStatus(int status) {
        this.isAdmin = status;
    }

    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroup> groups) {
        this.groups = groups;
    }
}
