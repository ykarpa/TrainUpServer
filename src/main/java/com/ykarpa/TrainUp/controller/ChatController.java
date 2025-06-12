package com.ykarpa.TrainUp.controller;

import com.ykarpa.TrainUp.entity.Chat;
import com.ykarpa.TrainUp.entity.User;
import com.ykarpa.TrainUp.service.ChatService;
import com.ykarpa.TrainUp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/api/chats")
//@RequiredArgsConstructor
//public class ChatController {
//
//    private final ChatService chatService;
//    private final MessageService messageService;
//    private final UserService userService;
//
//    @GetMapping
//    public List<ChatDTO> getUserChats(Authentication authentication) {
//        User currentUser = userService.getCurrentUser(authentication);
//        return chatService.getChatsForUser(currentUser);
//    }
//
//    @GetMapping("/with/{userId}")
//    public ChatDTO getOrCreateChatWithUser(@PathVariable Long userId, Authentication authentication) {
//        User currentUser = userService.getCurrentUser(authentication);
//        User otherUser = userService.getUserById(userId);
//        Chat chat = chatService.getOrCreateChat(currentUser, otherUser);
//        return chatService.toDTO(chat);
//    }
//
//    @GetMapping("/{chatId}/messages")
//    public List<MessageDTO> getChatMessages(@PathVariable Long chatId, Authentication authentication) {
//        User currentUser = userService.getCurrentUser(authentication);
//        return messageService.getMessagesForChat(chatId, currentUser);
//    }
//
//    @PostMapping("/{chatId}/messages")
//    public MessageDTO sendMessage(
//            @PathVariable Long chatId,
//            @RequestBody MessageDTO messageDTO,
//            Authentication authentication
//    ) {
//        User sender = userService.getCurrentUser(authentication);
//        return messageService.sendMessage(chatId, sender, messageDTO.getContent());
//    }
//}

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/{chatId}")
    public Chat getChat(@PathVariable Long chatId) {
        return chatService.getChat(chatId);
    }

    @GetMapping("/user/{userId}")
    public List<Chat> getUserChats(@PathVariable Long userId) {
        return chatService.getUserChats(userId);
    }

    @PostMapping("/get-or-create")
    public Chat getOrCreateChat(@RequestBody Map<String, Long> ids) {
        return chatService.getOrCreateChat(ids.get("userId1"), ids.get("userId2"));
    }
}

