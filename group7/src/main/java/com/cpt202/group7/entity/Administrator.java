package com.cpt202.group7.entity;

import com.cpt202.group7.enumerator.Gender;

public class Administrator extends User{
    public Administrator(String username, String password, Gender gender) {
        super(username, password, gender);
    }
}
