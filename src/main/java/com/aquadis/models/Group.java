package com.aquadis.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lorenzo
 */
@Entity
@Table(name = "Group")
public class Group {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "group_admin")
    @Transient
    private User groupAdmin;

    // TODO: many to many
    @OneToMany
    private List<UserGroup> users;

    public Group() {

    }

    public Group(String name, User groupAdmin) {
        setName(name);
        setGroupAdmin(groupAdmin);
        setUsers(new ArrayList<User>(), groupAdmin);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public User getGroupAdmin() {
        return groupAdmin;
    }

    private void setGroupAdmin(User groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

//    public List<User> getUsers() {
//        return users;
//    }

    public void addUser(User user) {
//        users.add(user);
//        user.addGroup(this);
    }

    private void setUsers(List<User> users, User groupAdmin) {
//        this.users = users;
//        this.users.add(groupAdmin);
//        groupAdmin.addGroup(this);
    }
}
