package com.cpt202.group7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId;
    private Integer userId;
    private Timestamp createTime;
    private Integer petId;
    private Double totalPrice;
    private String state;
}
