package com.example.FavorateMovieService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Movie not found")
public class MovieNotFoundException extends Exception{
}
