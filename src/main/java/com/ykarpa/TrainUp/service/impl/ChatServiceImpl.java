package com.ykarpa.TrainUp.service.impl;

import com.ykarpa.TrainUp.entity.Chat;
import com.ykarpa.TrainUp.entity.User;
import com.ykarpa.TrainUp.repository.ChatRepository;
import com.ykarpa.TrainUp.repository.UserRepository;
import com.ykarpa.TrainUp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Chat> getUserChats(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return chatRepository.findByParticipant1OrParticipant2(user, user);
    }

    @Override
    public Chat getOrCreateChat(Long userId1, Long userId2) {
        User u1 = userRepository.findById(userId1).orElseThrow();
        User u2 = userRepository.findById(userId2).orElseThrow();

        return chatRepository.findByParticipant1AndParticipant2(u1, u2)
                .or(() -> chatRepository.findByParticipant2AndParticipant1(u1, u2))
                .orElseGet(() -> {
                    Chat newChat = new Chat();
                    newChat.setParticipant1(u1);
                    newChat.setParticipant2(u2);
                    return chatRepository.save(newChat);
                });
    }

    @Override
    public Chat getChat(Long chatId) {
        return chatRepository.getChatById(chatId);
    }
}
