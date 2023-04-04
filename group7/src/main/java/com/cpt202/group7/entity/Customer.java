package com.cpt202.group7.entity;

import com.cpt202.group7.enumerator.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.HashSet;


public class Customer extends User{
    // Primary Key
    private Integer id;

    private HashSet<Pet> pets = new HashSet<>();
    private HashSet<Appointment> appointments = new HashSet<>();

    public Customer(String username, String password, String nickname, Gender gender, Integer age, String phone) {
        super(username, password, nickname, gender, age, phone);
    }

    public HashSet<Pet> getPets() {
        return pets;
    }

    public void setPets(HashSet<Pet> pets) {
        this.pets = pets;
    }

    public HashSet<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(HashSet<Appointment> appointments) {
        this.appointments = appointments;
    }
}
