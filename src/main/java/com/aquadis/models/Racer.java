package com.aquadis.models;


import javax.persistence.*;

@Entity
@Table(name = "Racer")
public class Racer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "salary")
    private int salary;

    public Racer(){

    }

    public Racer(String firstName, String lastName, int salary){
        setFirstName(firstName);
        setLastName(lastName);
        setSalary(salary);
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

    public int getSalary() {
        return salary;
    }

    private void setSalary(int salary) {
        this.salary = salary;
    }
}
