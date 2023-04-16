package com.cpt202.group7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groomer {
    private Integer groomerId;
    private String name;
    private String gender;
    private Integer age;
    private Integer groomerStarLevelPriceCoefficientId;
    private String selfIntroduction;
    private Timestamp workStartTime;
    private Timestamp workEndTime;
    private String photo;

    private boolean isWorking;
}
