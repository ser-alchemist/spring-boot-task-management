package com.aust.task.dto;

public class UserDTO {
    private Long uid_;
    private String uname;
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(Long uid_, String uname, String email, String password) {
        this.uid_ = uid_;
        this.uname = uname;
        this.email = email;
        this.password = password;
    }

    public UserDTO(String uname, String email, String password) {
        this.uname = uname;
        this.email = email;
        this.password = password;
    }

    public Long getUid_() {
        return uid_;
    }

    public void setUid_(Long uid_) {
        this.uid_ = uid_;
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
