package com.talkative.service;

import com.talkative.entity.EmailVerification;
import com.talkative.entity.Users;
import com.talkative.repository.EmailVerificationRepository;
import com.talkative.utility.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpServiceImpl implements OtpService{

    @Autowired
    public EmailVerificationRepository emailVerificationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveOtp(Users user) {

        String otpCode = generateOtp();

        System.out.println(otpCode);

        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setUser(user);
        emailVerification.setOtpCode(passwordEncoder.encode(otpCode));
        emailVerification.setExpiration(LocalDateTime.now().plusMinutes(MessageConstants.OTP_EXPIRATION_TIME)); // OTP valid for 10 minutes
        emailVerificationRepository.save(emailVerification);
    }

    public boolean verifyOtp(Users users, String otp) {

        Optional<EmailVerification> emailVerification = emailVerificationRepository.findByUserAndOtpCode(users, passwordEncoder.encode(otp));

        return emailVerification.isPresent() && emailVerification.get().getExpiration().isAfter(LocalDateTime.now());
    }

    public String generateOtp() {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < MessageConstants.OTP_SIZE; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }
}
