package com.cpt202.group7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;
    // For Login
    private String username; // e-mail Format

    private String password; // ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$

    // User Info
    private String avatarImgLink; // URL of the Avatar Image; Default is System Avatar
    private String nickname; // Length <= 16;
    private String gender; // Male | Female | Secret

    private String phone; // Chinese Phone Number; ^1[3456789]\d{9}$; Length Must Be 11

    private String role;


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatarImgLink='" + avatarImgLink + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}
