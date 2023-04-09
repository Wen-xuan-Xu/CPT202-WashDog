package com.cpt202.group7.entity;

import lombok.Data;

@Data
public class User {
    // For Login
    private String username; // e-mail Format

    private String password; // ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$

    // User Info
    private String avatarImgLink; // URL of the Avatar Image; Default is System Avatar
    private String nickname; // Length <= 16;
    private String gender; // Male | Female | Secret

    private String phonenumber; // Chinese Phone Number; ^1[3456789]\d{9}$; Length Must Be 11

    private String role;


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatarImgLink='" + avatarImgLink + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phonenumber + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}
