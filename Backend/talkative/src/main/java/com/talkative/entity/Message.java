package com.talkative.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String content;
	
	private LocalDateTime timestamp;

	private String mediaUrl;

	private Enum messageType;
	
	@ManyToOne      // user can create multiple messages. thats why many to one
	private Users senderId;
	
	@ManyToOne
	//@JoinColumn(name="chat_id")
	private Chat chatId;


	
}

/*
id	BIGINT (PK)	Unique identifier for the message
sender_id	BIGINT (FK)	References the Users table
chat_id	BIGINT (FK)	References either individual chat or group chat
content	TEXT	Text content of the message
media_url	VARCHAR(255)	URL to any attached media (images/videos)
timestamp	TIMESTAMP	When the message was sent
message_type	ENUM	TEXT, IMAGE, VIDEO (to handle media type)*/