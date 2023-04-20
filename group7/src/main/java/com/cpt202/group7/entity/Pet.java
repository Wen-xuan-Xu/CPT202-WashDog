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
public class Pet {
    // Primary Key
    @TableId("petId")
    private Integer petId;

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", userId=" + userId +
                ", petTypeId=" + petTypeId +
                ", sex='" + sex + '\'' +
                ", size='" + size + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", tips='" + tips + '\'' +
                '}';
    }

    @TableField("userId")
    private Integer userId;
    @TableField("petTypeId")
    private Integer petTypeId; // The Breed Of the Pet
    private String sex; // Male or Female
    private String size; // > 0
    private Integer age; // 1-50
    private String name; // Length <= 16
    private String tips; // The Note of the pet, max 150 words

    @TableField(exist = false)
    private String type;
    @TableField(exist = false)
    private String iconURL;

}
