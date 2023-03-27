/*
 * Author Name: Deepak Vishwkarma
 * Date: 16-02-2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.example.MovieService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class CommonUser {
    private String email;
    private String username;
    private long phoneNumber;
    private String password;

}
