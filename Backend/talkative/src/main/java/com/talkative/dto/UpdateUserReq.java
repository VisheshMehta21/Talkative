package com.talkative.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserReq {

	private String firstName;
	private String lastName;
	private String profilePicture;
	
}
