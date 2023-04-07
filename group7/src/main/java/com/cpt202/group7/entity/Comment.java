package com.cpt202.group7.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Comment {
    private Integer id;

    private User creator;
    private Timestamp createTime;

    private Integer starLevel;
    private String content;


}
