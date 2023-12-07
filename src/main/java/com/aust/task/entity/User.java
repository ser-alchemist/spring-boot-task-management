package com.aust.task.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {


    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")

    private Long id;

    @Column(name = "uname")
    private String uname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String uname, String email, String password) {

        this.uname = uname;
        this.email = email;
        this.password = password;
    }

    public Long getUid() {
        return id;
    }

    public void setUid(Long uid) {
        this.id = uid;
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

    @Override
    public String toString(){
        return "User [id: "+id+", username: "+uname+", email: "+email+", password: "+password+"]";
    }
}
