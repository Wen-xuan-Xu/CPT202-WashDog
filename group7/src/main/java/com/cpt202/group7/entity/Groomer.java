package com.cpt202.group7.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
@Data
public class Groomer {
    private Integer groomerId;
    private String name;
    private String gender;
    private Integer age;
    private Integer groomerStarLevelPriceCoefficientId;
    private String selfIntroduction;

    private Timestamp workStartTime;
    private Timestamp workEndTime;

    private boolean isWorking;
}
