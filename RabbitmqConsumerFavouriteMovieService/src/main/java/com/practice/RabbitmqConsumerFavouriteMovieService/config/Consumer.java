/*
 * Author Name: Rohit
 * Date: 10-12-2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.practice.RabbitmqConsumerFavouriteMovieService.config;


import com.practice.RabbitmqConsumerFavouriteMovieService.domain.User;
import com.practice.RabbitmqConsumerFavouriteMovieService.service.FavouriteMovieServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private FavouriteMovieServiceImpl favouriteMovieService;

    @Autowired
    public Consumer(FavouriteMovieServiceImpl favouriteMovieService) {
        this.favouriteMovieService = favouriteMovieService;
    }

    @RabbitListener(queues = "user_movie_queue")
    public void getData(UserDTO userDTO)
    {

        User user=new User();
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        favouriteMovieService.registerUser( user);

    }
}
