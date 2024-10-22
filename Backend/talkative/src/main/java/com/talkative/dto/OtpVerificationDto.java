package com.talkative.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerificationDto {

    private String otpCode;
    private String email;
}
