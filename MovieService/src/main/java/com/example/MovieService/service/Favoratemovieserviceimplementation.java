package com.example.MovieService.service;


import com.example.MovieService.model.CommonUser;
import com.example.MovieService.model.Movie;
import com.example.MovieService.model.User;
import com.example.MovieService.model.UserDTO;
import com.example.MovieService.proxi.Proxi;
import com.example.MovieService.repository.Movierepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Favoratemovieserviceimplementation implements Favoratemovieservice {
    @Autowired
    Movierepository movierepository;

    @Autowired
    private Proxi proxi;

    @Override
    public User registerUser(CommonUser commonUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmailid(commonUser.getEmailid());
        userDTO.setUsername(commonUser.getUsername());
        userDTO.setMobilenumber(commonUser.getMobilenumber());
        userDTO.setPassword(commonUser.getPassword());
        ResponseEntity<?> response = proxi.sendUserObjectToAuthApp(userDTO);
        System.out.println(response);

        User user = new User(commonUser.getEmailid(), commonUser.getUsername(), commonUser.getMobilenumber(), commonUser.getPassword(), new ArrayList<>());
        return movierepository.insert(user);
    }

    @Override
    public User addMovie(Movie movie, String userEmail) {
        User user = movierepository.findById(userEmail).get();


        if (user.getMovies() == null) {
            user.setMovies(Arrays.asList(movie));

        } else {

            List<Movie> contentsList = user.getMovies();
            contentsList.add(movie);
            user.setMovies(contentsList);
        }
        return movierepository.save(user);
    }

    @Override
    public User deleteMovies(String userEmail, String contentname) throws Exception {
        boolean contectIdExists = false;

        if (movierepository.findById(userEmail).isEmpty()) {
            throw new Exception("User Not Available");
        }

        User user = movierepository.findById(userEmail).get();

        List<Movie> contentsList = user.getMovies();

        contectIdExists = contentsList.removeIf(i -> i.getMoviename().equals(contentname));

        if (!contectIdExists) {
            throw new Exception("movies not available");
        }
        user.setMovies(contentsList);
        return movierepository.save(user);
    }

    @Override
    public List<Movie> getAllMovies(String userEmail) throws Exception {
        return movierepository.findById(userEmail).get().getMovies();
    }

//    @Override
//    public List<Movie> getMoviesName(String moviename) throws Exception {
//        return movierepository.findById(moviename).get().getMovies();
//    }
//

    @Override
    public User getMoviesByEmailId(String emailid, int movieid) throws Exception {
        boolean contectIdExists = false;

//        if (movierepository.findById(emailid).isEmpty()) {
//            throw new Exception("User Not Available");
//        }

        User user = movierepository.findById(emailid).get();

        List<Movie> contentsList = user.getMovies();

        // contectIdExists = contentsList.removeIf(i -> i.getMoviename().equals(moviename));

//        if (!contectIdExists) {
//            throw new Exception("movies not available");
//        }
        user.setMovies(contentsList);
        return movierepository.save(user);
    }
//    @Override
//    public List<Movie> getMoviesName(String moviename) throws Exception {
//        return movierepository.findById(moviename).get().getMovies();
//    }
//
//    @Override
//    public List<Movie> getTrackname2(String moviename) {
//        return movierepository.findMovieName(moviename);
//    }
}
