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
    private Integer appointmentId;
    @TableField("serviceId")
    private Integer serviceId;
    @TableField("groomerId")
    private Integer groomerId;
    @TableField("orderId")
    private Integer orderId;
    @TableField("date")
    private Timestamp date;
    @TableField("startTime")
    private Timestamp startTime;
    @TableField("endTime")
    private Timestamp endTime;

}
