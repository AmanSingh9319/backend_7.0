package com.niit.UserAuth.service;

import com.niit.UserAuth.Exception.UserNotFoundException;
import com.niit.UserAuth.model.User;

public interface UserService {
    public User addUser(User user);

    User updateUser(String email,User user) throws UserNotFoundException;
    public User login(String email, String password);

    User createNewPassword(String email, String password);

}
