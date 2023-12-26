package com.aust.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aust.task.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUname(String uname);
    User findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
