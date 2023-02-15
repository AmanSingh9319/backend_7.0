package com.niit.UserAuth.service;

import com.niit.UserAuth.model.User;

public interface UserService {
    public User addUser(User user);

    public User login(String userEmail, String password);
}
