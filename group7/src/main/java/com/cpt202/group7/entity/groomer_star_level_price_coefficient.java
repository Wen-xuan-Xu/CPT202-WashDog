package com.cpt202.group7.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("groomer_star_level_price_coefficient")
public class groomer_star_level_price_coefficient {
    @TableId("groomerStarLevelPriceCoefficientId")
    private Integer groomerStarLevelPriceCoefficientId;
    @TableField("priceCoefficient")
    private Integer priceCoefficient;
}
