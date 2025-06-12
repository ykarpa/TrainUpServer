package com.ykarpa.TrainUp.controller;

import com.ykarpa.TrainUp.entity.User;
import com.ykarpa.TrainUp.entity.UserMeasurement;
import com.ykarpa.TrainUp.repository.UserMeasurementRepository;
import com.ykarpa.TrainUp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/measurements")
public class UserMeasurementController {

    @Autowired
    private UserMeasurementRepository repo;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public List<UserMeasurement> getAll(@PathVariable Long userId) {
        return repo.findByUserIdOrderByDateAsc(userId);
    }

    @PostMapping
    public UserMeasurement save(@RequestBody UserMeasurement measurement) {
        if (measurement.getUser() == null || measurement.getUser().getId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        User user = userRepository.findById(measurement.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        measurement.setUser(user); // асоціюємо user вручну
        return repo.save(measurement);
    }

    @GetMapping("/latest/{userId}")
    public UserMeasurement getLatest(@PathVariable Long userId) {
        return repo.findFirstByUserIdOrderByDateDesc(userId).orElse(null);
    }

    @GetMapping("/additional/latest/{userId}")
    public List<UserMeasurement.AdditionalMeasurement> getLatestAdditional(@PathVariable Long userId) {
        return repo.findFirstByUserIdOrderByDateDesc(userId)
                .map(UserMeasurement::getAdditionalMeasurements)
                .orElseGet(Collections::emptyList);
    }
}

