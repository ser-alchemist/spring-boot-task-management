package com.aust.task.services;

import com.aust.task.dto.LoginDTO;
import com.aust.task.dto.LoginMessage;
import com.aust.task.dto.UserDTO;
import com.aust.task.entity.User;
import com.aust.task.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<User> userList;
    public List<User> getUserList(){
        return userList;
    }

    public String  addUser(UserDTO userDTO){
        User user = new User(
                userDTO.getUid_(),
                userDTO.getUname(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
        return user.getUname();
    }

    public long getUid(String email){
        User user = userRepository.findByEmail(email);
        return user.getUid();
    }
    UserDTO userDTO;

    public LoginMessage loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepository.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("Password Not Match", false);
            }
        }else {
            return new LoginMessage("Email not exits", false);
        }
    }




}
