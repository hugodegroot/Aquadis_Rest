package com.aquadis.models;

import javax.persistence.*;
import java.util.List;

/**
 * @author Lorenzo
 */
@Entity
@Table(name = "tb_Group")
public class Group {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<UserGroup> users;

    public Group() {

    }

    public Group(String name) {
        setName(name);
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

    public List<UserGroup> getUsers() {
        return users;
    }

    public void setUsers(List<UserGroup> users) {
        this.users = users;
    }
}
