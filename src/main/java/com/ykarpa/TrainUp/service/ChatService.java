package com.ykarpa.TrainUp.service;

import com.ykarpa.TrainUp.entity.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> getUserChats(Long userId);
    Chat getOrCreateChat(Long userId1, Long userId2);

    Chat getChat(Long chatId);
}
