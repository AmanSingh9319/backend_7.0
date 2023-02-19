package com.example.MovieService.controller;


import com.example.MovieService.model.CommonUser;
import com.example.MovieService.model.Movie;
import com.example.MovieService.service.Favoratemovieservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class Moviecontroller {

    @Autowired
    Favoratemovieservice favoratemovieservice;

    private ResponseEntity<?> responseEntity;

    //http://localhost:8085/api/v2/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CommonUser commonUser) {
        responseEntity = new ResponseEntity<>(favoratemovieservice.registerUser(commonUser), HttpStatus.CREATED);
        return responseEntity;
    }

    //http://localhost:8085/api/v2/add/akash12@gmail.com
    @PostMapping("/add/{emailid}")
    public ResponseEntity<?> addEmail(@RequestBody Movie movie, @PathVariable String emailid) {
        responseEntity = new ResponseEntity<>(favoratemovieservice.addMovie(movie, emailid), HttpStatus.CREATED);
        return responseEntity;
    }

    //http://localhost:8085/api/v2/delete/akash12@gmail.com/kgf2
    @DeleteMapping("/delete/{emailid}/{moviename}")
    public ResponseEntity<?> deleteMovie(@PathVariable String emailid, @PathVariable String moviename) throws Exception {
        responseEntity = new ResponseEntity<>(favoratemovieservice.deleteMovies(emailid, moviename), HttpStatus.OK);
        return responseEntity;
    }

    //http://localhost:8085/api/v2/get/akash12@gmail.com
    @GetMapping("/get/{emailid}")
    public ResponseEntity<?> getAllUser(@PathVariable String emailid) throws Exception {
        responseEntity = new ResponseEntity<>(favoratemovieservice.getAllMovies(emailid), HttpStatus.OK);
        return responseEntity;
    }

    //    @GetMapping("/get/{moviename}")
//    public ResponseEntity<?> getMovieName(@PathVariable String moviename) throws Exception {
//        responseEntity = new ResponseEntity<>(favoratemovieservice.getMoviesName(moviename), HttpStatus.OK);
//        return responseEntity;
//    }
//
//
    //http://localhost:8085/api/v2/get/akash12@gmail.com/kgf2
    @GetMapping("/get/{emailid}/{movieid}")
    public ResponseEntity<?> getMovieByName(@PathVariable String emailid, @PathVariable int movieid) throws Exception {
        responseEntity = new ResponseEntity<>(favoratemovieservice.getMoviesByEmailId(emailid, movieid), HttpStatus.OK);
        return responseEntity;
    }
//    @GetMapping("/search/{moviename}")
//    public ResponseEntity<?> serachmovies(@PathVariable String moviename) {
//
//        return new ResponseEntity<>(favoratemovieservice.getTrackname2(moviename), HttpStatus.OK);
//    }

}
