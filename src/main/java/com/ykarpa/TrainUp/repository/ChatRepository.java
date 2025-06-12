package com.ykarpa.TrainUp.repository;

import com.ykarpa.TrainUp.entity.Chat;
import com.ykarpa.TrainUp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByParticipant1OrParticipant2(User participant1, User participant2);

    Optional<Chat> findByParticipant1AndParticipant2(User p1, User p2);
    Optional<Chat> findByParticipant2AndParticipant1(User p2, User p1);

    Chat getChatById(Long chatId);
}

