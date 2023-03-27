package com.example.MovieService.rabbitmq;


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
public class MovieDTO
{
    @Id
    private int movieid;
    private String moviename;
    private int movierating;
    private String movielanguage;

}
