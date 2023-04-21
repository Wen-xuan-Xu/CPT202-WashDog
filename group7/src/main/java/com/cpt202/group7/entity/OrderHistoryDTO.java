package com.cpt202.group7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderHistoryDTO {
    private String orderId;
    private String servicesSummary;
    private String groomerName;
    private String photo;
    private Timestamp createTime;
    private String status;
    private Double price;
}
