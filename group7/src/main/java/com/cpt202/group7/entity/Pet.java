package com.cpt202.group7.entity;


import lombok.Data;

@Data
public class Pet {
    // Primary Key
    private Integer petId;
    private Integer userId;
    private Integer petTypeId; // The Breed Of the Pet
    private String sex; // Male or Female
    private Double weight; // > 0
    private Integer age; // 1-50
    private String name; // Length <= 16
    private String tips; // The Note of the pet, max 150 words
}
