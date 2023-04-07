package com.cpt202.group7.entity;

public class Customer extends User{
    public Customer(String username, String password, String gender){
        super(username, password, gender);
    }

    public Customer(String username, String password, String gender, String nickname) {
        super(username, password, gender, nickname);
    }
}
