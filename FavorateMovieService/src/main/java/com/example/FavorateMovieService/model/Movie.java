package com.example.FavorateMovieService.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
public class Movie
{
    @Id
    private int movieId;
    private String movieName;
    private String movierating;
    private String movielanguage;

    public Movie() {
    }

    public Movie(int movieId, String movieName, String movierating, String movielanguage) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movierating = movierating;
        this.movielanguage = movielanguage;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovierating() {
        return movierating;
    }

    public void setMovierating(String movierating) {
        this.movierating = movierating;
    }

    public String getMovielanguage() {
        return movielanguage;
    }

    public void setMovielanguage(String movielanguage) {
        this.movielanguage = movielanguage;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieid=" + movieId +
                ", moviename='" + movieName + '\'' +
                ", movierating='" + movierating + '\'' +
                ", movielanguage='" + movielanguage + '\'' +
                '}';
    }
}
