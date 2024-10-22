package com.talkative.utility;

public final class MessageConstants {

    public static final String OTP_SENT_SUCCESSFULLY = "OTP sent successfully.";
    public static final String OTP_VERIFIED_SUCCESSFULLY = "OTP verified successfully.";
    public static final String INVALID_OTP = "Invalid OTP.";
    public static final String SIGNUP_SUCCESSFUL = "Signup successful, please check your email for the OTP.";
    public static final String USER_EXISTS = "User with this email already exists. Please login or signup with different email";
    public static final Integer OTP_SIZE = 6;
    public static final Integer OTP_EXPIRATION_TIME = 60 * 10; // in seconds

    // Add other messages as needed

    private MessageConstants() {
        // Private constructor to prevent instantiation
    }
}
