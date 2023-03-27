package com.example.MovieService.controller;


import com.example.MovieService.Exception.MovieAlreadyExistsException;
import com.example.MovieService.Exception.UserAlreadyExistsException;
import com.example.MovieService.Exception.UserNotFoundException;
import com.example.MovieService.model.CommonUser;
import com.example.MovieService.model.Movie;
import com.example.MovieService.model.User;
import com.example.MovieService.service.FavouriteMovieservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class Moviecontroller {

    @Autowired
    FavouriteMovieservice favoratemovieservice;

    private ResponseEntity<?> responseEntity;

    //http://localhost:8085/api/v2/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CommonUser commonUser) throws UserAlreadyExistsException {
        try {
            responseEntity = new ResponseEntity<>(favoratemovieservice.registerUser(commonUser), HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e)
        {
            throw new UserAlreadyExistsException();
//            responseEntity=new ResponseEntity<>("this user already exists",HttpStatus.OK);
        }
        return responseEntity;
    }

    //http://localhost:8085/api/v2/add/akash12@gmail.com
    @PostMapping("/add/{email}")
    public ResponseEntity<?> addmovieByEmail(@RequestBody Movie movie, @PathVariable String email) throws MovieAlreadyExistsException {

        try {
            responseEntity = new ResponseEntity<>(favoratemovieservice.addMovie(movie, email), HttpStatus.CREATED);
        }
        catch (MovieAlreadyExistsException e) {
            throw new MovieAlreadyExistsException();
        }

        return responseEntity;
    }

    //http://localhost:8085/api/v2/delete/akash12@gmail.com/kgf2
    @DeleteMapping("/delete/{email}/{moviename}")
    public ResponseEntity<?> deleteMovie(@PathVariable String email, @PathVariable String moviename) throws Exception {
        responseEntity = new ResponseEntity<>(favoratemovieservice.deleteMovies(email, moviename), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/deletebyid/{email}/{movieId}")
    public ResponseEntity<?> deleteFavourateMoviebyId(@PathVariable String email,@PathVariable int movieId ) throws UserNotFoundException, Exception {
       try {
           responseEntity = new ResponseEntity<>(favoratemovieservice.deleteMoviebyMovieId(movieId,email),HttpStatus.OK);
       }
       catch (UserNotFoundException e){
           throw new UserNotFoundException();
       }
       catch (Exception e){
           responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
           throw new Exception();
       }
        return responseEntity;
    }


    //http://localhost:8085/api/v2/get/akash12@gmail.com
    @GetMapping("/getMovielist/{email}")
    public ResponseEntity<?> getAllUser(@PathVariable String email) throws Exception {
        responseEntity = new ResponseEntity<>(favoratemovieservice.getAllMovies(email), HttpStatus.OK);
        return responseEntity;
    }

    //http://localhost:8085/api/v2/get/akash12@gmail.com/kgf2
    @GetMapping("/getUser/{email}")
    public ResponseEntity<?> getMovieByEmail(@PathVariable String email) throws Exception {
        responseEntity = new ResponseEntity<>(favoratemovieservice.getMoviesByEmailId(email), HttpStatus.OK);
        return responseEntity;
    }



    @PostMapping("/add1/{email}")
    public ResponseEntity<?> addmovie(@RequestBody Movie movie, @PathVariable String email) throws MovieAlreadyExistsException {

        try {
            responseEntity = new ResponseEntity<>(favoratemovieservice.addMovie1(email,movie), HttpStatus.CREATED);
        }
        catch (MovieAlreadyExistsException e) {
            throw new MovieAlreadyExistsException();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            responseEntity =new ResponseEntity<>("try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }


    @PostMapping("/updateuserdetails/{email}")
    public ResponseEntity<?> updateUser( @PathVariable String email,@RequestBody User user) throws UserNotFoundException {

        try {
            responseEntity = new ResponseEntity<>(favoratemovieservice.updateUserDetails(email,user), HttpStatus.OK);
        }
        catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }

        return responseEntity;
    }

    //http://localhost:8085/api/v2/getOTP/akash12@gmail.com/8082456780
    @GetMapping("/getOTP/{email}")
    public ResponseEntity<?> otpGenerate(@PathVariable String email) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(favoratemovieservice.generateOneTimePassword(email), HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @PostMapping("/setPassword/{email}/{password}")
    ResponseEntity<?> generateNewpassword(@PathVariable String email,@PathVariable String password){
        responseEntity=new ResponseEntity<>(favoratemovieservice.generateNewPassword(email, password),HttpStatus.CREATED);
        return responseEntity;
    }

}
