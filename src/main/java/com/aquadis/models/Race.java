package com.aquadis.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Race")
public class Race {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String Name;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;
}
