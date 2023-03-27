package com.niit.UserAuth.controller;

import com.niit.UserAuth.Exception.UserNotFoundException;
import com.niit.UserAuth.model.User;
import com.niit.UserAuth.security.SecurityTokenGenerator;
import com.niit.UserAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    private ResponseEntity<?> responseEntity;


    //http://localhost:8082/user/v1/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    //http://localhost:8082/user/v1/login
    // @CrossOrigin(origins = "http://localhost:52110")
    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody User user) {
        User result = userService.login(user.getEmail(), user.getPassword());
        if (result != null) {
            Map<String, String> key = securityTokenGenerator.generateToken(user);
            return new ResponseEntity<>(key, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/updateuserdetails/{email}")
    public ResponseEntity<?> userUpdate( @PathVariable String email,@RequestBody User user) throws UserNotFoundException {

        try {
            responseEntity = new ResponseEntity<>(userService.updateUser(email,user), HttpStatus.OK);
        }
        catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }

        return responseEntity;
    }


    @PostMapping("/setPassword/{email}/{password}")
    ResponseEntity<?> createNewpassword(@PathVariable String email,@PathVariable String password){
        responseEntity=new ResponseEntity<>(userService.createNewPassword(email, password),HttpStatus.CREATED);
        return responseEntity;
    }
}
