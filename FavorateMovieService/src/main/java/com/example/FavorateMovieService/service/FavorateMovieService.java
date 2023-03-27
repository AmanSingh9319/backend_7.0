package com.example.FavorateMovieService.service;

//import com.example.FavorateMovieService.exception.;
//import com.example.FavorateMovieService.exception.MovieNotFoundException;
import com.example.FavorateMovieService.exception.MovieNotFoundException;
import com.example.FavorateMovieService.model.Movie;

import java.util.List;
import java.util.Optional;

public interface FavorateMovieService
{
    Movie saveMovieToFavorates(Movie movie);
    boolean deleteMovieFromFavorates(int movieId,String movieName) throws MovieNotFoundException;
    List<Movie> getAllFavorateMovies(int movieId) ;
    //Optional<Movie> getByMovieid(int movieid);
}
