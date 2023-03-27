package com.example.MovieService.service;


import com.example.MovieService.Config.Producer;
import com.example.MovieService.EmailAPI.EmailService;
import com.example.MovieService.Exception.MovieAlreadyExistsException;
import com.example.MovieService.Exception.MovieNotFoundException;
import com.example.MovieService.Exception.UserAlreadyExistsException;
import com.example.MovieService.Exception.UserNotFoundException;
import com.example.MovieService.model.*;
import com.example.MovieService.proxi.Proxi;
import com.example.MovieService.rabbitmq.MovieDTO;
import com.example.MovieService.repository.MovieRepository;
import com.example.MovieService.repository.OtpRepository;
import com.example.MovieService.repository.UserMovieRepository;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class FavouriteMovieserviceImplementation implements FavouriteMovieservice {

    @Autowired
    UserMovieRepository userMovieRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    private Proxi proxi;

    EmailService emailService=new EmailService();

    OTP otpGen=new OTP();

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    @Autowired
    Producer producer;

    @Autowired
    public FavouriteMovieserviceImplementation(Proxi proxi, RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.proxi = proxi;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }


    @Override
    public User registerUser(CommonUser commonUser) throws UserAlreadyExistsException {

        User user1;

        if(userMovieRepository.findById(commonUser.getEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        else{
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(commonUser.getEmail());
        userDTO.setUsername(commonUser.getUsername());
        userDTO.setPhoneNumber(commonUser.getPhoneNumber());
        userDTO.setPassword(commonUser.getPassword());
        ResponseEntity<?> response = proxi.sendUserObjectToAuthApp(userDTO);
        System.out.println(response);

        user1 = new User(commonUser.getEmail(), commonUser.getUsername(), commonUser.getPhoneNumber(), commonUser.getPassword(), new ArrayList<>());}
        emailService.sendEmail(commonUser);
        return userMovieRepository.insert(user1);
    }

    @Override
    public User addMovie(Movie movie, String userEmail) throws MovieAlreadyExistsException {
        User user = userMovieRepository.findById(userEmail).get();

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieid(movie.getMovieId());
        movieDTO.setMovielanguage(movie.getMovielanguage());
        movieDTO.setMovierating(movie.getMovierating());

//        if(userRepository.findById(product.getProductId()).isPresent()) {
//            throw new ProductAlreadyExistException();
//        }
        List<Movie> movieList1;
        movieList1 = user.getMovieList();

        if (movieList1 == null) {

            movieList1.add(movie);

        } else {

//            List<Movie> movieList = user.getMovieList();
//
//            ListIterator<Movie> iterator=movieList1.listIterator();
//            while (iterator.hasNext())
//            {
//                Movie movie1=iterator.next();
//                if(movie1.getMovieId()==movie.getMovieId())
//                {
//                    throw new MovieAlreadyExistsException();
//                }
//                break;
//            }
            movieList1.add(movie);
            user.setMovieList(movieList1);
        }
        return userMovieRepository.save(user);
    }

    @Override
    public User deleteMovies(String userEmail, String moviename) throws Exception {
        boolean contectIdExists = false;

        if (userMovieRepository.findById(userEmail).isEmpty()) {
            throw new Exception("User Not Available");
        }

        User user = userMovieRepository.findById(userEmail).get();

        List<Movie> contentsList = user.getMovieList();

        contectIdExists = contentsList.removeIf(i -> i.getMovieName().equals(moviename));

        if (!contectIdExists) {
            throw new Exception("movies not available");
        }
        user.setMovieList(contentsList);
        return userMovieRepository.save(user);
    }


    @Override
    // Deleting a movie from the favourite list.
    public User deleteMoviebyMovieId(int movieId, String email) throws UserNotFoundException, MovieNotFoundException {
        boolean result;

        if (userMovieRepository.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = userMovieRepository.findById(email).get();
        List<Movie> movieList = user.getMovieList();
        result = movieList.removeIf(obj -> obj.getMovieId() == movieId);

        if (!result) {
            throw new MovieNotFoundException();
        }

        user.setMovieList(movieList);
        return userMovieRepository.save(user);

    }


    @Override
    public List<Movie> getAllMovies(String userEmail) {
        return userMovieRepository.findById(userEmail).get().getMovieList();
    }


    @Override
    public User getMoviesByEmailId(String emailid) throws Exception {
//        boolean contectIdExists = false;
        User user = userMovieRepository.findById(emailid).get();

        List<Movie> movieList = user.getMovieList();
        user.setMovieList(movieList);
        return userMovieRepository.save(user);
    }

    @Override
    public User updateUserDetails(String email, User user) throws UserNotFoundException {
        Optional<User> user1=userMovieRepository.findById(email);
        User userMain=user1.get();
        if(user1.isPresent())
        {

            String username1;
             long phoneNumber1;
             long phoneNumber2=0;
             String email1;
             String password1;
             List<Movie> movieList1=user1.get().getMovieList();


             if(user.getPassword().isEmpty())
             { password1=user1.get().getPassword();
             } else{
                 password1=user.getPassword();
             }

            if(user.getEmail().isEmpty())
            { email1=user1.get().getEmail();
            } else{
                email1=user.getEmail();
            }



            if(user.getUsername().isEmpty())
            { username1=user1.get().getUsername();
            } else{
                username1=user.getUsername();
            }

            if(phoneNumber2 == user.getPhoneNumber()){
                phoneNumber1=user1.get().getPhoneNumber();
            }

            else {
                phoneNumber1=user.getPhoneNumber();
            }

             userMain.setEmail(email1);
             userMain.setUsername(username1);
             userMain.setPhoneNumber(phoneNumber1);
             userMain.setPassword(password1);
             userMain.setMovieList(movieList1);
            ResponseEntity<?> response = proxi.userUpdate(email,userMain);
            System.out.println(response);
        }
        else
        {
            throw new UserNotFoundException();
        }
         User userMain2=userMovieRepository.save(userMain);
        return userMain2;
    }

    public User addMovie1(String email, Movie movie) throws MovieAlreadyExistsException {

        User user = userMovieRepository.findById(email).get();
        List<Movie> movieList1 = user.getMovieList();
        boolean flag = false;
        ListIterator<Movie> iterator = movieList1.listIterator();

        if(movieList1==null)
        {
            movieList1.add(movie);
        }


        else {

            while (iterator.hasNext()) {
                Movie favMovie = iterator.next();
                if (favMovie.getMovieId() == movie.getMovieId()) {
                    flag=true;
                }

            }

            if(flag==true){
                throw new MovieAlreadyExistsException();
            }

            movieList1.add(movie);
            user.setMovieList(movieList1);
        }


         return userMovieRepository.save(user);
    }

    public String generateOneTimePassword(String email) throws UserNotFoundException{
        User user1=userMovieRepository.findById(email).get();
//             long phoneNumber2=user1.getPhoneNumber();
             String otp1;
        if(userMovieRepository.findById(email).isPresent()){
            otp1=generateOTP();
            otpGen.setOtp(otp1);
            otpRepository.save(otpGen);

        }
        else {
            throw new UserNotFoundException();
        }
        emailService.sendOTP(user1,otp1);
        return otp1;
    }

    @Override
    public User generateNewPassword(String email, String password) {
        User user1=userMovieRepository.findById(email).get();
        user1.setPassword(password);
        ResponseEntity<?> response = proxi.createNewpassword(email,password);
        System.out.println(response);
        emailService.sendPasswordNotification(user1,password);
        userMovieRepository.save(user1);
        return user1;
    }


    public String generateOTP(){
        int randomNo=(int) (Math.random()*9000)+1000;
        String otp=String.valueOf(randomNo);
        return otp;
    }

}
