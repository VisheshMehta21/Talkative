package com.talkative.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignupRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
