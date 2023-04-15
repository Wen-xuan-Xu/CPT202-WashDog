package com.cpt202.group7.entity;


import lombok.Data;

@Data
public class Service {
    private Integer serviceId;

    private String name;

    private Double price;
    private Integer duration;//minutes
}
