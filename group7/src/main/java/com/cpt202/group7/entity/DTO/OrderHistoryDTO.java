package com.cpt202.group7.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryDTO {
    private String servicesSummary;
    private String groomerName;
    private String groomerPhoto;
    private Timestamp createTime;
    private String status;
    private Double price;

}
