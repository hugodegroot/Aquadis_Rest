package com.aquadis.models;

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
    private List<RacePosition> positions;

//    @OneToMany(mappedBy = "raceMax", fetch = FetchType.EAGER)
//    private List<MaxPositionPrediction> maxPositionPredictions;

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

//    public List<MaxPositionPrediction> getMaxPositionPredictions() {
//        return maxPositionPredictions;
//    }
//
//    public void setMaxPositionPredictions(List<MaxPositionPrediction> maxPositionPredictions) {
//        this.maxPositionPredictions = maxPositionPredictions;
//    }
}
