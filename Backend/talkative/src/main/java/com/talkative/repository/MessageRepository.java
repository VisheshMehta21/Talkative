package com.talkative.repository;

import com.talkative.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>{

	@Query("Select m from Message m join m.chat c where c.id=:chatId")
	public List<Message> findByChatId(@Param("chatId") Long chatId);
}
