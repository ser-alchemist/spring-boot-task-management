package com.aust.task.services;

import com.aust.task.entity.User;
import com.aust.task.student.Student;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class UserService {


    private enum field{
        EMAIL,
        PASSWORD
    }

    private List<User> userList = new ArrayList<>(Arrays.asList(
            new User(1L, "maria", "maria.jamal@gmail.com", "1234"),
            new User(2L, "marzia", "marzia.jamal@gmail.com", "5678")
    ));


    public List<User> getUserList(){
        return userList;
    }

    public boolean addUser(User user){
        try{
            userList.add(user);
            return true;
        }catch(Exception e){
            return false;
        }
    }


    public boolean updateUser(field f, Long uid, String newVal){
        try {
            for (User user : userList) {

                if (user.getUid().equals(uid)) {

                    if (f == field.EMAIL) {
                        user.setEmail(newVal);
                        return true;

                    } else if (f == field.PASSWORD) {
                        user.setPassword(newVal);
                        return true;
                    }
                }
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }

}
