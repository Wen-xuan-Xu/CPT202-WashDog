package com.cpt202.group7.entity;


import lombok.Data;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    private Integer orderId;
    private Integer userId;
    private Timestamp createTime;
    private Integer petId;
    private Double totalPrice;
    private String state;

}
