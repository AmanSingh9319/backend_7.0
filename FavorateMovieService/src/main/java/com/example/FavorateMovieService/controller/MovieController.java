package com.example.FavorateMovieService.controller;

import com.example.FavorateMovieService.exception.MovieAlreadyExistsException;
import com.example.FavorateMovieService.exception.MovieNotFoundException;
import com.example.FavorateMovieService.model.Movie;
import com.example.FavorateMovieService.service.FavorateMovieService;
import com.example.FavorateMovieService.service.FavorateMovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class MovieController {
    @Autowired
    FavorateMovieServiceImpl favorateMovieService;

    private ResponseEntity<?> responseEntity;

    public MovieController(FavorateMovieServiceImpl favorateMovieService) {
        this.favorateMovieService = favorateMovieService;
    }

    @PostMapping("/savemovie")
    ResponseEntity<?> Addmovie(@RequestBody Movie movie) throws MovieAlreadyExistsException{
        return new ResponseEntity<>(favorateMovieService.saveMovieToFavorates(movie), HttpStatus.CREATED);
    }

    @GetMapping("/fetchmovies/{movieId}")
    ResponseEntity<?> fetchFavoratemovies(@PathVariable int movieId){
        return new ResponseEntity<>(favorateMovieService.getAllFavorateMovies(movieId),HttpStatus.FOUND);
    }

    @GetMapping("/fetchmoviesbymovieid/{movieId}")
    ResponseEntity<?> fetchFavorateMoviebymovieid(@PathVariable int movieId){
        return new ResponseEntity<>(favorateMovieService.getAllFavorateMovies(movieId),HttpStatus.FOUND);
    }

    @DeleteMapping("/deletefavoratemovies/{movieId}/{emailid}")
    ResponseEntity<?> deleteFavorateMovie(@PathVariable int movieId,@PathVariable String emailid){
        try
        {
        responseEntity=new ResponseEntity<>(favorateMovieService.deleteMovieFromFavorates(movieId,emailid),HttpStatus.OK);
          }

        catch(MovieNotFoundException e)
        {
            responseEntity=new ResponseEntity<>("MovieDTO not found",HttpStatus.NOT_FOUND);
        }

        catch (Exception e){
            responseEntity=new ResponseEntity<>("Error try after some time",HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return responseEntity;


    }

}
