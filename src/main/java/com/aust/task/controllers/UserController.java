package com.aust.task.controllers;

import com.aust.task.entity.User;
import com.aust.task.services.UserService;
import com.aust.task.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path = "/show")
    public List<User> getUser() {
        return userService.getUserList();
    }

    @PostMapping(path = "/add")
    public List<User> createStudent(@RequestBody User user){

        boolean status = userService.addUser(user);

        if(status)
            return userService.getUserList();
        else
            return null;
    }



}
