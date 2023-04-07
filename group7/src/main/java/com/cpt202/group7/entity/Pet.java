package com.cpt202.group7.entity;



public class Pet {
    // Primary Key
    private Integer id;

    private String name; // Length <= 16
    private String breed; // The Breed Of the Pet
    private Integer age; // 1-50
    private String sex; // Male or Female
    private Double weight; // > 0

    private Customer owner; // The owner of the pet
    private String specification; // The Note of the pet, max 150 words

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
