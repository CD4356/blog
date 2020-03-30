package com.cd.blog.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {

    /*自增id*/
    private Integer userId;
    /*昵称*/
    private String nickName;
    /*用户名*/
    private String username;
    /*密码*/
    private String password;
    /*邮箱*/
    private String email;
    /*用户头像*/
    private String userImg;
    /*创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /*最近更新时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastEditTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}