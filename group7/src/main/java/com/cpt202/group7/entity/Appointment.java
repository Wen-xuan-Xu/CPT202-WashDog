package com.cpt202.group7.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @TableField("appointmentId")
    @TableId("appointmentId")
    private Integer appointmentId;
    @TableField("serviceId")
    private Integer serviceId;
    @TableField("groomerId")
    private Integer groomerId;
    @TableField("orderId")
    private String orderId;

    @TableField(exist = false)
    private String groomerName;

    @TableField(exist = false)
    private String serviceName;


}
