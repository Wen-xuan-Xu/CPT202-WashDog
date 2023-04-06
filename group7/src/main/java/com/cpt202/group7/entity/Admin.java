package com.cpt202.group7.entity;

import com.cpt202.group7.enumerator.Gender;

public class Admin extends User{
    public Admin(String username, String password, String nickname, Gender gender, Integer age, String phone) {
        super(username, password, nickname, gender, age, phone);
    }
}
