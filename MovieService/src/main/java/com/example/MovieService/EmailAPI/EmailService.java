package com.example.MovieService.EmailAPI;


import com.example.MovieService.model.CommonUser;
import com.example.MovieService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.SimpleMailMessage;

import java.util.Properties;

public class EmailService {

    private JavaMailSender mailSender;
    @Autowired
    public EmailService() {
        this.mailSender = createMailSender();
    }

    private JavaMailSender createMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("movieflixwave34@gmail.com");
        mailSender.setPassword("baipoatwmosofggp");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        System.out.println(mailSender);
        return mailSender;
    }

    public void sendEmail(CommonUser user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Welcome to the MovieFlix");
        message.setText("Dear "+ user.getUsername()
                + ",\n\nThank you for registering with MovieFlix with email. "
                +user.getEmail()+" and password "+user.getPassword()+
                " We extend our gratitude for choosing our services. ThankYOU!!");
        System.out.println(message);
        System.out.println("Before calling of mail sender instance inside sendEmail() method");
        mailSender.send(message);
    }


    public void sendOTP(User user, String otp){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("User verification for login");
        message.setText("Dear "+ user.getUsername() + ",\n\n MovieFlix has autherised your emailid,the one time password for email verification is "+otp+
                " We extend our gratitude for choosing our services. ThankYOU!!"    );
        System.out.println(message);
        System.out.println("Before calling of mail sender instance inside sendOTP() method");
        mailSender.send(message);

    }

    public void sendPasswordNotification(User user, String password){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("User verification for login");
        message.setText("Dear "+ user.getUsername() + ",\n\n you have changed your password successfully , now you can login with the new password :"+password+
                " . We extend our gratitude for choosing our services. ThankYOU!!"    );
        System.out.println(message);
        System.out.println("Before calling of mail sender instance inside sendOTP() method");
        mailSender.send(message);

    }
}