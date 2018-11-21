package com.aquadis.models;

import javax.persistence.*;
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

    @Column(name = "month")
    private String month;

    @Column(name = "start_day")
    private int startDay;

    @Column(name = "end_day")
    private int endDay;

    @OneToMany(mappedBy = "race", fetch = FetchType.EAGER)
    private List<RacePosition> positions;

    public Race() {
    }

    public Race(String name, String month, int start, int end) {
        setName(name);
        setMonth(month);
        setStartDay(start);
        setEndDay(end);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    private void setMonth(String month) {
        this.month = month;
    }

    public int getStartDay() {
        return startDay;
    }

    private void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    private void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public List<RacePosition> getPositions() {
        return positions;
    }

    public void setPositions(List<RacePosition> positions) {
        this.positions = positions;
    }
}
