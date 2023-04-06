package com.cpt202.group7.entity;

import com.cpt202.group7.enumerator.Gender;

public class Customer extends User{
    public Customer(String username, String password, Gender gender) {
        super(username, password, gender);
    }
}
