package com.example.MovieService.repository;

import com.example.MovieService.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie,Integer> {

    Movie findByMovieId(int movieId);
}
