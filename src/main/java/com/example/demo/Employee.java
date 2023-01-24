package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Employee {
    private @Id @GeneratedValue Long id;
    private String name;
    private String lastName;
    private Date dateOfEmployment;

    public Employee(){

    }

    public Employee(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfEmployment = new Date();
    }

    //gettery i settery

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }
}
