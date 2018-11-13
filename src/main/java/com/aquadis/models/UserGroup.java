package com.aquadis.models;

import javax.persistence.*;

@Entity
@Table(name = "User_Group")
public class UserGroup {

    @Id
    @Column(name = "UserGroup_id")
    private int id;

    @Column(name = "points")
    private int points;

//    @ManyToOne
//    @JsonIgnore
//    private User user;
//
//    @ManyToOne
//    @JsonIgnore
//    private Group group;


    public UserGroup() {
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
