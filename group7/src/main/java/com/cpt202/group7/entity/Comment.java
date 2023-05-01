package com.cpt202.group7.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(autoResultMap = true)
public class Comment {
        @TableId("commentId")
        private Integer commentId;
        @TableField("userId")
        private Integer userId;
        @TableField(exist = false)
        private String username;
        private Timestamp time;
        private String content;
        @TableField("starLevel")
        private Integer starLevel;
        @TableField("orderId")
        private String orderId;
        @TableField(exist = false)
        private List<Appointment> appointments;

        // getters and setters


}
