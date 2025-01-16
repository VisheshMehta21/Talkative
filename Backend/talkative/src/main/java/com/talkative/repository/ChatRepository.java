package com.talkative.repository;

import java.util.List;

import com.talkative.entity.Chat;
import com.talkative.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRepository extends JpaRepository<Chat, Long> {

	@Query("select c from Chat c join c.users u where u.id =:userId")
	public List<Chat> findChatByUserId(@Param("userId") Long userId);
	
	@Query("SELECT c FROM Chat c WHERE c.isGroup = false AND :user2 MEMBER OF c.users AND :reqUser MEMBER OF c.users")
	public Chat findSingleChatByUserIds(@Param("user2") Users user2, @Param("reqUser") Users reqUser);
}
