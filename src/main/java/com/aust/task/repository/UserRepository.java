package com.aust.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aust.task.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUname(String uname);
    List<User> findByEmail(String email);
}
