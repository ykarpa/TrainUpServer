package com.ykarpa.TrainUp.controller;

import com.ykarpa.TrainUp.entity.Exercise;
import com.ykarpa.TrainUp.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping
    public Set<String> getUniqueExerciseNames() {
        return exerciseRepository.findDistinctExerciseNames();
    }
}
