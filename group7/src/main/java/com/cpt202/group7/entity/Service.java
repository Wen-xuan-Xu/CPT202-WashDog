package com.cpt202.group7.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(autoResultMap = true)
public class Service {
    @TableField("serviceId")
    private Integer serviceId;
    private String name;
    private Double price;
    private Integer duration;//minutes
    @TableField("briefIntroduction")
    private String briefIntroduction;
    @TableField("detailIntroduction")
    private String detailIntroduction;
}