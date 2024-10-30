package com.talkative.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersProfileUpdateDto {

    private String firstName;
    private String lastName;

}
