package com.aust.task.services;

import com.aust.task.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class UserService {

    private List<User> userList = new ArrayList<>(Arrays.asList(
            new User(1L, "maria", "maria.jamal@gmail.com", "1234"),
            new User(2L, "marzia", "marzia.jamal@gmail.com", "5678")
    ));





}
