package com.niit.UserAuth.service;

import com.niit.UserAuth.Exception.UserNotFoundException;
import com.niit.UserAuth.model.User;
import com.niit.UserAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String email, User user) throws UserNotFoundException {
//         User user1=userRepository.findById(email).get();
//         if(Optional.ofNullable(user1).isEmpty()){
//             throw new UserNotFoundException();
//         }

        Optional<User> user1=userRepository.findById(email);
        User userMain=user1.get();
        if(user1.isPresent())
        {

            String username1;
            long phoneNumber1;
            long phoneNumber2=0;
            String email1;
            String password1;

            if(user.getPassword().isEmpty())
            { password1=user1.get().getPassword();
            } else{
                password1=user.getPassword();
            }

            if(user.getEmail().isEmpty())
            { email1=user1.get().getEmail();
            } else{
                email1=user.getEmail();
            }



            if(user.getUsername().isEmpty())
            { username1=user1.get().getUsername();
            } else{
                username1=user.getUsername();
            }

            if(phoneNumber2 == user.getPhoneNumber()){
                phoneNumber1=user1.get().getPhoneNumber();
            }

            else {
                phoneNumber1=user.getPhoneNumber();
            }

            userMain.setEmail(email1);
            userMain.setUsername(username1);
            userMain.setPhoneNumber(phoneNumber1);
            userMain.setPassword(password1);

        }

        else
        {
            throw new UserNotFoundException();
        }
        User userMain2=userRepository.save(userMain);
        return userMain2;

    }

    @Override
    public User login(String email, String password) {
        if (userRepository.findById(email).isPresent()) {
            User user = userRepository.findById(email).get();
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


    public User createNewPassword(String email, String password) {
        User user1=userRepository.findById(email).get();
        user1.setPassword(password);
        userRepository.save(user1);
        return user1;
    }

}
