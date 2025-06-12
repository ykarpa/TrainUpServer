package com.ykarpa.TrainUp.controller;

import com.ykarpa.TrainUp.entity.User;
import com.ykarpa.TrainUp.repository.UserRepository;
import com.ykarpa.TrainUp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

//    @Autowired
//    public UserController(userService userService) {
//        this.userService = userService;
//    }

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Пошук користувачів за username або ім'ям/прізвищем
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String query) {
        return userRepository.findByUsernameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query, query);
    }

    // Призначення тренера
    @PutMapping("/{clientId}/assign-trainer")
    public ResponseEntity<String> assignTrainer(
            @PathVariable Long clientId,
            @RequestParam Long trainerId,
            @RequestParam(required = false) String location
    ) {
        userService.assignTrainerToClient(clientId, trainerId, location);
        return ResponseEntity.ok("Клієнта додано до тренера");
    }

}

