package com.aust.task.entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "uname")
})

public class User {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long uid_;

    @Column(name = "uname", nullable = false)
    private String uname;

    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<Task> tasks;
    public User() {
    }

    public User(String uname, String email, String password) {

        this.uname = uname;
        this.email = email;
        this.password = password;
    }

    public Long getUid() {
        return uid_;
    }

/*
    public void setUid(Long uid) {
        this.id = uid;
    }*/

    public String getUname() {
        return uname;
    }
/*

    public void setUname(String uname) {
        this.uname = uname;
    }
*/

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

/*    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }*/

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @Override
    public String toString(){
        return "User [id: "+uid_+", username: "+uname+", email: "+email+", password: "+password+"]";
    }
}
