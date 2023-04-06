package com.cpt202.group7.entity;

import com.cpt202.group7.enumerator.Gender;

public class User {
    // For Login
    private String username; // e-mail Format
    private String password; // ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$

    // User Info
    private String avatarImgLink = "System Avatar"; // URL of the Avatar Image; Default is System Avatar
    private String nickname; // Length <= 16;
    private Gender gender; // Male | Female | Secret

    private String phone; // Chinese Phone Number; ^1[3456789]\d{9}$; Length Must Be 11

    public User(String username, String password, Gender gender) {
        this.username = username;
        this.password = password;

        this.nickname = username;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarImgLink() {
        return avatarImgLink;
    }

    public void setAvatarImgLink(String avatarImgLink) {
        this.avatarImgLink = avatarImgLink;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
