package com.aquadis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Grid_Positition")
public class RacePosition {

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

    @Column(name = "match_time")
    private String time;

    @Column(name = "lap_time")
    private String beginTime;

    @Column(name = "extra_laps")
    private int laps;

    @Column(name = "status")
    private String Status;

    public RacePosition() {
    }

    public RacePosition(Race race, Racer racer, Position beginPosition, int bMinutes, int bSeconds, int bMilliseconds,
                        Position endPosition, int hours, int minutes, int seconds, int milliseconds, int laps, String status) {
        setRacer(racer);
        setRace(race);
        setBeginPosition(beginPosition);
        setEndPosition(endPosition);

        try {

            // Sets the laptimes
            LapTime lapTime = new LapTime();
            lapTime.setTime(0, bMinutes, bSeconds, bMilliseconds);
            setBeginTime(lapTime.getTime());
            lapTime.setTime(hours, minutes, seconds, milliseconds);
            setTime(lapTime.getTime());

            setLaps(laps);
            setStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    private void setBeginPosition(Position beginPosition) {
        this.beginPosition = beginPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    private void setEndPosition(Position endPosition) {
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

    public String getBeginTime() {
        return beginTime;
    }

    private void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public int getLaps() {
        return laps;
    }

    private void setLaps(int laps) {
        this.laps = laps;
    }

    public String getStatus() {
        return Status;
    }

    private void setStatus(String status) {
        Status = status;
    }
}
