package com.example.MovieService.repository;

import com.example.MovieService.model.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtpRepository extends MongoRepository<OTP,String> {
}
