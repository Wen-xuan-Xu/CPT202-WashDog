package com.cpt202.group7.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("pet_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class pet_type {
    @TableId("petTypeId")
    private Integer petTypeId;
    private String type;
    @TableField("iconURL")
    private String iconURL;
}
