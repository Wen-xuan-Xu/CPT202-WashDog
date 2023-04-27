package com.cpt202.group7.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@TableName("`order`")
public class Order {
    @TableField("orderId")
    @TableId(value = "orderId")
    private String orderId;
    @TableField("userId")
    private Integer userId;
    @TableField("createTime")
    private Timestamp createTime;
    @TableField("petId")
    private Integer petId;
    @TableField("totalPrice")
    private Double totalPrice;
    @TableField("state")
    private String state;
    @TableField("startTime")
    private Timestamp startTime;
    @TableField("endTime")
    private Timestamp endTime;
}
