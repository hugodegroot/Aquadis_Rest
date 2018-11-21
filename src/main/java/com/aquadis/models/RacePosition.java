package com.aquadis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Grid_Positition")
public class GridPosition {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "race_id")
    private Race race;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "begin_position_id")
    private Position beginPosition;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "end_position_id")
    private Position endPosition;

    @OneToOne
    @JoinColumn(name = "racer_id")
    private Racer racer;

    @Column(name = "lap_time")
    private String time;

    public GridPosition() {
    }

    public GridPosition(Race race, Position beginPosition, Position endPosition, Racer racer, int minutes, int seconds, int milliseconds) {
        setRacer(racer);
        setRace(race);
        setBeginPosition(beginPosition);
        setEndPosition(endPosition);
        LapTime lapTime = new LapTime(minutes, seconds, milliseconds);
        setTime(lapTime.getTime());
    }

    public Race getRace() {
        return race;
    }

    private void setRace(Race race) {
        this.race = race;
    }

    public Position getBeginPosition() {
        return beginPosition;
    }

    public void setBeginPosition(Position beginPosition) {
        this.beginPosition = beginPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Position endPosition) {
        this.endPosition = endPosition;
    }

    public Racer getRacer() {
        return racer;
    }

    private void setRacer(Racer racer) {
        this.racer = racer;
    }

    public String getTime() {
        return time;
    }

    private void setTime(String time) {
        this.time = time;
    }
}
