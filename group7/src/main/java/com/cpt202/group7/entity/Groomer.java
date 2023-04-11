package com.cpt202.group7.entity;

import java.sql.Timestamp;
import java.util.HashSet;

public class Groomer {
    private Integer groomer_id;
    private String name;
    private String gender;
    private Integer age;
    private Integer groomer_star_level_price_coefficient_id;
    private String self_introduction;

    private Timestamp work_start_time;
    private Timestamp work_end_time;

    private boolean is_working;
}
