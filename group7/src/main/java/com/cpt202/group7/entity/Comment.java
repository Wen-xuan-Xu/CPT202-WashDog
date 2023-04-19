package com.cpt202.group7.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(autoResultMap = true)
public class Comment {
    private Integer commentId;
    private Integer userId;

    private Timestamp time;

    private String content;

    private Integer starLevel;

    private Integer groomerId;
    private Integer serviceId;
}
