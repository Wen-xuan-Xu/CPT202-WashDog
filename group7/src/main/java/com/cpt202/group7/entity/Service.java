package com.cpt202.group7.entity;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

public class Service {
    // Primary Key
    private Integer id;

    private String name; // Service Name
    private Double price; // The Price of the Service

    private HashSet<Timestamp> times = new HashSet<>(); // Service Available Time
    private HashSet<Groomer> groomers = new HashSet<>(); // The Groomers that can offer the service
    private HashSet<String> petTypes = new HashSet<>(); // What breed of the pet can be served

    public Service(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public HashSet<Timestamp> getTimes() {
        return times;
    }

    public void setTimes(HashSet<Timestamp> times) {
        this.times = times;
    }

    public HashSet<Groomer> getGroomers() {
        return groomers;
    }

    public void setGroomers(HashSet<Groomer> groomers) {
        this.groomers = groomers;
    }

    public HashSet<String> getPetTypes() {
        return petTypes;
    }

    public void setPetTypes(HashSet<String> petTypes) {
        this.petTypes = petTypes;
    }
}
