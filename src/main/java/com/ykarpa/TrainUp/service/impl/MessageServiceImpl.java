package com.ykarpa.TrainUp.service.impl;

import com.ykarpa.TrainUp.entity.Chat;
import com.ykarpa.TrainUp.entity.Message;
import com.ykarpa.TrainUp.entity.User;
import com.ykarpa.TrainUp.repository.ChatRepository;
import com.ykarpa.TrainUp.repository.MessageRepository;
import com.ykarpa.TrainUp.repository.UserRepository;
import com.ykarpa.TrainUp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Message> getMessages(Long chatId) {
        Chat chat = chatRepository.findById(chatId).orElseThrow();
        return messageRepository.findByChatOrderByTimestampAsc(chat);
    }

    @Override
    public Message sendMessage(Long chatId, Long senderId, String content) {
        Chat chat = chatRepository.findById(chatId).orElseThrow();
        User sender = userRepository.findById(senderId).orElseThrow();

        Message msg = new Message();
        msg.setChat(chat);
        msg.setSender(sender);
        msg.setContent(content);
        msg.setTimestamp(new Date());

        return messageRepository.save(msg);
    }
}
