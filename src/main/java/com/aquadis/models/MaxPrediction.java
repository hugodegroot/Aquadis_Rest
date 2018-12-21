package com.aquadis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "User_Race")
public class MaxPrediction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User userMax;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "race_id")
    private Race raceMax;

    private String raceName;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "position")
    private Position position;

    private String place;

    public MaxPrediction() {

    }

    public MaxPrediction(User user, Race race, Position position) {
        setUserMax(user);
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setRaceMax(race);
        setRaceName(race.getName());
        setPosition(position);
        setPlace(position.getPlace());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserMax() {
        return userMax;
    }

    private void setUserMax(User user) {
        this.userMax = user;
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

    public Race getRaceMax() {
        return raceMax;
    }

    private void setRaceMax(Race race) {
        this.raceMax = race;
    }

    public String getRaceName() {
        return raceName;
    }

    private void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public Position getPosition() {
        return position;
    }

    private void setPosition(Position position) {
        this.position = position;
    }

    public String getPlace() {
        return place;
    }

    private void setPlace(String place) {
        this.place = place;
    }
}
