package com.example.MovieService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class OTP
{
    @Id
    private String otp;

    public OTP() {
    }

    public OTP(String otp) {
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "OTP{" +
                "otp='" + otp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OTP otp1 = (OTP) o;
        return Objects.equals(otp, otp1.otp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(otp);
    }
}
