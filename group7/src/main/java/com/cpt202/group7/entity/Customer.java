package com.cpt202.group7.entity;

import com.cpt202.group7.enumerator.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.HashSet;


public class Customer extends User{
    public Customer(String username, String password, String nickname, Gender gender, Integer age, String phone) {
        super(username, password, nickname, gender, age, phone);
    }
}
