package com.cpt202.group7.entity;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(autoResultMap = true)
public class Groomer {

    @TableField("groomerId")
    @TableId("groomerId")
    private Integer groomerId;
    private String name;
    private String gender;
    private Integer age;
    @TableField("groomerStarLevelPriceCoefficientId")
    private Integer groomerStarLevelPriceCoefficientId;
    @TableField("selfIntroduction")
    private String selfIntroduction;
    @TableField("workStartTime")

    private Time workStartTime;
    @TableField("workEndTime")
    private Time workEndTime;

    @TableField(value="isWorking")
    private boolean isWorking;

    @TableField("briefIntroduction")
    private String briefIntroduction;
    @TableField("detailIntroduction")
    private String detailIntroduction;

    private String photo;
    public boolean isIsWorking() {
        return isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }


}
