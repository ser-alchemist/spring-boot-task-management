package com.aust.task.controllers;

import com.aust.task.entity.User;
import com.aust.task.repository.UserRepository;
import com.aust.task.services.UserService;
//import com.aust.task.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/")
public class UserController {

    //private final UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username){
        try{
            List<User> users = new ArrayList<User>();

            if (username==null) {
                userRepository.findAll().forEach(users::add);
            }
            else {
                userRepository.findByUname(username).forEach(users::add);
            }


            if (users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        Optional<User> userData = userRepository.findById(id);

        if(userData.isPresent()){
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            System.out.println(user.toString());
            User _user = userRepository.save(new User(user.getUname(), user.getEmail(), user.getPassword()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }








    /*
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path = "/show")
    public List<User> getUser() {
        return userService.getUserList();
    }

    //method to add user
    @PostMapping(path = "/add")
    public List<User> createStudent(@RequestBody User user){

        boolean status = userService.addUser(user);
        if(status)
            return userService.getUserList();
        else
            return null;
    }

     */



}
