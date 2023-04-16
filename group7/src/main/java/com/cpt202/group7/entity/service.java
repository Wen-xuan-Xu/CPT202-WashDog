package com.cpt202.group7.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class service {
    private Integer serviceId;

    private String name;

    private Double price;
    private Integer duration;//minutes
    private String briefIntroduction;
    private String detailIntroduction;



}
