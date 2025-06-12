package com.ykarpa.TrainUp.repository;

import com.ykarpa.TrainUp.entity.Chat;
import com.ykarpa.TrainUp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatOrderByTimestampAsc(Chat chat);
}
