package com.aquadis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Race")
public class Race {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "start_day")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_day")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @OneToMany(mappedBy = "race", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<RacePosition> positions;

    @OneToMany(mappedBy = "race", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserGroup> users;

    @OneToMany(mappedBy = "race", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<UserGroup> groups;

    @OneToMany(mappedBy = "raceMax", fetch = FetchType.EAGER)
//    @Fetch(value = FetchMode.SUBSELECT)
    private List<MaxPrediction> maxPositionPredictions;

    public Race() {
    }

    public Race(String name, String location, Date start, Date end) {
        setName(name);
        setLocation(location);
        setStartDate(start);
        setEndDate(end);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    private void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    private void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<RacePosition> getPositions() {
        return positions;
    }

    public void setPositions(List<RacePosition> positions) {
        this.positions = positions;
    }

    public List<UserGroup> getUsers() {
        return users;
    }

    public void setUsers(List<UserGroup> users) {
        this.users = users;
    }

    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroup> groups) {
        this.groups = groups;
    }

    public List<MaxPrediction> getMaxPositionPredictions() {
        return maxPositionPredictions;
    }

    public void setMaxPositionPredictions(List<MaxPrediction> maxPositionPredictions) {
        this.maxPositionPredictions = maxPositionPredictions;
    }
}
