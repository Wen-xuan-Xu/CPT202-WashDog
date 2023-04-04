package com.cpt202.group7.entity;

import java.util.HashSet;

public class Groomer {
    // Primary Key
    private Integer id;

    private String name;
    private String gender;
    private Integer age;
    private Integer starLevel;
    private String selfIntroduction;

    private HashSet<Service> services = new HashSet<>();
    private HashSet<String> availableTimes =new HashSet<>();

    public Groomer(String name, String gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.starLevel = 3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public HashSet<Service> getServices() {
        return services;
    }

    public void setServices(HashSet<Service> services) {
        this.services = services;
    }

    public HashSet<String> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(HashSet<String> availableTimes) {
        this.availableTimes = availableTimes;
    }
}
