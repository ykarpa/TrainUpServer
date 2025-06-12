package com.ykarpa.TrainUp.repository;

import com.ykarpa.TrainUp.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query("SELECT DISTINCT e.name FROM Exercise e WHERE e.name IS NOT NULL")
    Set<String> findDistinctExerciseNames();
}