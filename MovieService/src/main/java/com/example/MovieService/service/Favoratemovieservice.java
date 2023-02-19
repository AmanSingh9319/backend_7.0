package com.example.MovieService.service;


import com.example.MovieService.model.CommonUser;
import com.example.MovieService.model.Movie;
import com.example.MovieService.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Favoratemovieservice {


    User registerUser(CommonUser commonUser);

    User addMovie(Movie movie, String userEmail);

    User deleteMovies(String userEmail, String contentname) throws Exception;

    List<Movie> getAllMovies(String userEmail) throws Exception;


    User getMoviesByEmailId(String emailid, int movieid) throws Exception;

//    List<Movie> getMoviesName(String movieid) throws Exception;
//
//    List<Movie> getTrackname2(String moviename);
}
