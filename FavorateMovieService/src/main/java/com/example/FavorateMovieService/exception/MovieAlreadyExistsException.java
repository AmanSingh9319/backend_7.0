package com.example.FavorateMovieService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Movie already exist")
public class MovieAlreadyExistsException extends Throwable{
}
