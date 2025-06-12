package com.ykarpa.TrainUp.service;

import com.ykarpa.TrainUp.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    void assignTrainerToClient(Long clientId, Long trainerId, String location);

//    User createUser(User user);
    User getUserById(Long id);
    User updateUser(Long id, User updatedUser);
    User getUserByUsernameOrEmail(String usernameOrEmail);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}