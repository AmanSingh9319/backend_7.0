package com.example.MovieService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode

public class Movie {
    @Id
    private int movieid;
    private String moviename;
    private int movierating;
    private String movielanguage;

}
