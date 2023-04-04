package com.cpt202.group7.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.HashSet;


public class Customer extends User{
    // Primary Key
    private Integer id;

    private HashSet<Pet> petHashSet;

}
