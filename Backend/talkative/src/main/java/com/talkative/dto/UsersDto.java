package com.talkative.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    public String firstName;
    public String lastName;
    public String profileUrl;
    public String email;
    public LocalDateTime createdAt;

}
