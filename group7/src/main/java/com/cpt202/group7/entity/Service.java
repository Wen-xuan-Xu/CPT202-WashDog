package com.cpt202.group7.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private Integer serviceId;
    private String name;
    private Double price;
    private Integer duration;//minutes
    private String briefIntroduction;
    private String detailIntroduction;
}