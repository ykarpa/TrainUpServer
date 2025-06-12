package com.ykarpa.TrainUp.service;

import com.ykarpa.TrainUp.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMessages(Long chatId);
    Message sendMessage(Long chatId, Long senderId, String content);
}
