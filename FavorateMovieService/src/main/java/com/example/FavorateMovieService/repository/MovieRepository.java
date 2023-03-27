package com.example.FavorateMovieService.repository;

import com.example.FavorateMovieService.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie,Integer> {

    Movie findByMovieId(int movieId);
    Movie findByMoviename(String movieName);
    List<Movie> findAllMoviesByMovieId(int movieId);
    boolean deleteMovieByMovieidAndMoviename(int movieId,String movieName);
}
