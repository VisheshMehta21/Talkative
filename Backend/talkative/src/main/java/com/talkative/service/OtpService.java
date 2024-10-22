package com.talkative.service;


import com.talkative.entity.Users;

public interface OtpService {

    public void saveOtp(Users user);
    public boolean verifyOtp(Users users, String otp);
}
