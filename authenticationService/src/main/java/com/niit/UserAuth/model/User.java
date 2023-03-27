package com.niit.UserAuth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    //@GeneratedValue
    //  private int id;
    private String email;
    private String username;
    private long phoneNumber;
    private String password;

}
