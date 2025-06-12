package com.ykarpa.TrainUp.repository;

import com.ykarpa.TrainUp.entity.TrainingProgram;
import com.ykarpa.TrainUp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, Long> {
    Optional<TrainingProgram> findById(Long id);
    List<TrainingProgram> findAll();
}
