package com.example.FavorateMovieService.service;

import com.example.FavorateMovieService.exception.MovieNotFoundException;
import com.example.FavorateMovieService.model.Movie;
import com.example.FavorateMovieService.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavorateMovieServiceImpl implements FavorateMovieService {
    @Autowired

    private MovieRepository movieRepository;

    public FavorateMovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovieToFavorates(Movie movie) {
        Movie movie1 = movieRepository.save(movie);

//        if(movieRepository.findById(movie.getMovieid()).isPresent()){
//
//            if(movieRepository.findByMovieid(movie.getMovieid()).getEmailid().equals(movie.getEmailid())){
//                throw new MovieAlreadyExistsException();
//            }
//        }
        return movie1;
    }

    @Override
    public boolean deleteMovieFromFavorates(int movieId, String movieName) throws MovieNotFoundException {
        Movie movie = movieRepository.findByMovieId(movieId);

        boolean result = false;
        if (movie == null) {
            throw new MovieNotFoundException();
        } else {
            movieRepository.deleteMovieByMovieidAndMoviename(movieId, movieName);
            result = true;
        }

        return true;
    }

    @Override
    public List<Movie> getAllFavorateMovies(int movieId) {
        return movieRepository.findAllMoviesByMovieId(movieId);

    }

//    @Override
//    public Optional<Movie> getByMovieid(int movieid) {
//        return movieRepository.findById(movieid);
//    }
//
}
