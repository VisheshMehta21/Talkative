package com.talkative.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {

    private String email;
    private String message;
}
