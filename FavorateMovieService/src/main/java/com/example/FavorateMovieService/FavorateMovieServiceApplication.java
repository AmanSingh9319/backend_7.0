package com.example.FavorateMovieService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@SpringBootApplication

@SpringBootApplication
@EnableEurekaClient
public class FavorateMovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavorateMovieServiceApplication.class, args);
	}

}
