package com.talkative.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatReq {

	private List<String> emails;
	private String chatName;
	private String chatImage;
}
