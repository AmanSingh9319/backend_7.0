package com.niit.UserAuth.service;

import com.niit.UserAuth.model.User;
import com.niit.UserAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(String userEmail, String password) {
        if (userRepository.findById(userEmail).isPresent()) {
            User user = userRepository.findById(userEmail).get();
            if (user.getPassword().equals(password)) {
                user.setPassword("");
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
