package com.cpt202.group7.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId("userId")
    private Integer userId;
    // For Login
    private String username; // e-mail Format

    private String password; // ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$

    // User Info

    private String nickname; // Length <= 16;
    private String gender; // Male | Female | Secret

    private String phone; // Chinese Phone Number; ^1[3456789]\d{9}$; Length Must Be 11

    private String role;

    private String photo;


}
