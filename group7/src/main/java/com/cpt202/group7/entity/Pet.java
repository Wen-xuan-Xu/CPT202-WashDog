package com.cpt202.group7.entity;


import lombok.Data;

@Data
public class Pet {
    // Primary Key
    private Integer id;

    private String name; // Length <= 16
    private String breed; // The Breed Of the Pet
    private Integer age; // 1-50
    private String sex; // Male or Female
    private Double weight; // > 0

    private User owner; // The owner of the pet
    private String specification; // The Note of the pet, max 150 words


}
