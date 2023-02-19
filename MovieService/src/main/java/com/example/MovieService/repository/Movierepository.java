package com.example.MovieService.repository;

import com.example.MovieService.model.Movie;
import com.example.MovieService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Movierepository extends MongoRepository<User, String> {
    @Query("{'movies.moviename':{$in:[?0]}}")
        // @Query("{'product.productname':{$in:[?0]}}")
    List<Movie> findMovieName(String moviename);
}
