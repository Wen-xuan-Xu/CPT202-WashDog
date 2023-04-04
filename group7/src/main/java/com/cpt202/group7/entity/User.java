package com.cpt202.group7.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class User {
    // For Login
    private String username; // e-mail Format
    private String password; // ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$

    // User Info
    private String avatarImgLink; // URL of the Avatar Image
    private String nickname; // Length <= 16;
    private String gender; // Male | Female | Secret
    private Integer age; // 0 ~ 150

    private String phone; // Phone Number; Default +86 ***********; Length Must Be 11

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
