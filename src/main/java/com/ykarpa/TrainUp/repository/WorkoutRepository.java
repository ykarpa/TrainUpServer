package com.ykarpa.TrainUp.repository;

import com.ykarpa.TrainUp.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserIdOrderByDateDesc(Long userId);

    List<Workout> findByUserId(Long id);
}

