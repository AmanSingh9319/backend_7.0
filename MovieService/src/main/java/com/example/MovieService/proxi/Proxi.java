package com.example.MovieService.proxi;

import com.example.MovieService.model.User;
import com.example.MovieService.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service", url = "localhost:8083")
public interface Proxi {
    @PostMapping("/user/v1/register")
    public abstract ResponseEntity<?> sendUserObjectToAuthApp(@RequestBody UserDTO userDTO);


    @PostMapping("/user/v1/updateuserdetails/{email}")
    public ResponseEntity<?> userUpdate(@PathVariable("email")String email, @RequestBody User user);

    @PostMapping("/user/v1/setPassword/{email}/{password}")
    ResponseEntity<?> createNewpassword(@PathVariable String email,@PathVariable String password);
}