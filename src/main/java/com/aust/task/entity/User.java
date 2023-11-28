package com.aust.task.entity;

public class User {

    private Long uid;
    private String uname;
    private String email;
    private String password;


    public User() {
    }

    public User(Long uid, String uname, String email, String password) {
        this.uid = uid;
        this.uname = uname;
        this.email = email;
        this.password = password;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
