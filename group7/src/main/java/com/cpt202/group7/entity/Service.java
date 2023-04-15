package com.cpt202.group7.entity;


import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

@Data
public class Service {
    private Integer serviceId;

    private String name;

    private Double price;
    private Integer duration;//minutes
    private String briefIntroduction;
    private String detailIntroduction;



}
