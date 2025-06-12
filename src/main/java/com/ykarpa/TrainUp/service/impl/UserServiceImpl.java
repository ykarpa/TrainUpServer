package com.ykarpa.TrainUp.service.impl;

import com.ykarpa.TrainUp.entity.User;
import com.ykarpa.TrainUp.repository.UserRepository;
import com.ykarpa.TrainUp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void assignTrainerToClient(Long clientId, Long trainerId, String location) {
        User client = userRepository.findById(clientId).orElseThrow();
        User trainer = userRepository.findById(trainerId).orElseThrow();

        System.out.println(client);

        client.setTrainer(trainer);
        client.setTrainingStartDate(LocalDate.now()); // <-- додано поле
        if (location != null) {
            if (client.getLocations() == null) client.setLocations(new ArrayList<>());
            client.getLocations().add(location);
        }
        userRepository.save(client);
    }

//    @Override
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setRoles(updatedUser.getRoles());
            return userRepository.save(user);
        }).orElse(null);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}