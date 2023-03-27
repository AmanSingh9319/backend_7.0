package com.example.MovieService.service;


import com.example.MovieService.Exception.MovieAlreadyExistsException;
import com.example.MovieService.Exception.MovieNotFoundException;
import com.example.MovieService.Exception.UserAlreadyExistsException;
import com.example.MovieService.Exception.UserNotFoundException;
import com.example.MovieService.model.CommonUser;
import com.example.MovieService.model.Movie;
import com.example.MovieService.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavouriteMovieservice {


    User registerUser(CommonUser commonUser) throws UserAlreadyExistsException;

    User addMovie(Movie movie, String userEmail) throws MovieAlreadyExistsException;

    User deleteMovies(String userEmail, String moviename) throws Exception;
    public User deleteMoviebyMovieId(int movieId, String email) throws UserNotFoundException, MovieNotFoundException;

    List<Movie> getAllMovies(String userEmail);

    User addMovie1(String email, Movie movie) throws MovieAlreadyExistsException;
    User getMoviesByEmailId(String email) throws Exception;

    User updateUserDetails(String email,User user) throws UserNotFoundException;
    public String generateOneTimePassword(String email) throws UserNotFoundException;

    User generateNewPassword(String email,String password);
}
