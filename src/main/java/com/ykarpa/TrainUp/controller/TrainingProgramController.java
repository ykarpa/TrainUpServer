package com.ykarpa.TrainUp.controller;

import com.ykarpa.TrainUp.entity.TrainingProgram;
import com.ykarpa.TrainUp.repository.TrainingProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
public class TrainingProgramController {
    private final TrainingProgramRepository programRepository;

    @GetMapping
    public List<TrainingProgram> getAll() {
        return programRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingProgram> getById(@PathVariable Long id) {
        return programRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

