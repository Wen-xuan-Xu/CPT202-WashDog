package com.cpt202.group7.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Comment {
    private Integer commentId;
    private Integer userId;

    private Timestamp time;

    private String content;

    private Integer starLevel;

    private Integer groomerId;
    private Integer serviceId;
}
