package com.ykarpa.TrainUp.controller;

import com.ykarpa.TrainUp.entity.Message;
import com.ykarpa.TrainUp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/chat/{chatId}")
    public List<Message> getMessages(@PathVariable Long chatId) {
        return messageService.getMessages(chatId);
    }

    @PostMapping("/chat/{chatId}/send")
    public Message sendMessage(@PathVariable Long chatId, @RequestBody Map<String, String> payload) {
        Long senderId = Long.valueOf(payload.get("senderId"));
        String content = payload.get("content");
        return messageService.sendMessage(chatId, senderId, content);
    }
}

