package com.cpt202.group7.entity;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

public class Service {
    // Primary Key
    private Integer id;

    private String name; // Service Name
    private Double price; // The Price of the Service

    public Service(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
