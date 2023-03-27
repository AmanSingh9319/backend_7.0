package com.practice.RabbitmqConsumerFavouriteMovieService.service;

import com.practice.RabbitmqConsumerFavouriteMovieService.domain.User;
import com.practice.RabbitmqConsumerFavouriteMovieService.repository.UserMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteMovieServiceImpl implements FavouriteMovieservice{

    @Autowired
    UserMovieRepository userMovieRepository;

    @Autowired
    FavouriteMovieservice favouriteMovieservice;

    @Override
    public User registerUser(User user) {
        return userMovieRepository.save(user);
    }

//    public FavouriteMovieServiceImpl(UserMovieRepository userMovieRepository, FavouriteMovieservice favouriteMovieservice) {
//        this.userMovieRepository = userMovieRepository;
//        this.favouriteMovieservice = favouriteMovieservice;
//    }

//    @Override
//    public User registerUser(com.practice.RabbitmqConsumerFavouriteMovieService.domain.User user) {
//        return userMovieRepository.save(user);
//    }


}
