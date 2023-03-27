package com.practice.RabbitmqConsumerFavouriteMovieService.repository;


//import org.apache.catalina.User;
import com.practice.RabbitmqConsumerFavouriteMovieService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserMovieRepository extends MongoRepository<User, String> {

}
