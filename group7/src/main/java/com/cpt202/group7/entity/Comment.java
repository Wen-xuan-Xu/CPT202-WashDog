package com.cpt202.group7.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Comment {
    private Integer comment_id;
    private Integer user_id;

    private Timestamp time;

    private String content;

    private Integer star_level;

    private Integer groomer_id;
    private Integer service_id;
}
