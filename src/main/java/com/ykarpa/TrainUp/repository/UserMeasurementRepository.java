package com.ykarpa.TrainUp.repository;

import com.ykarpa.TrainUp.entity.UserMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMeasurementRepository extends JpaRepository<UserMeasurement, Long> {
    List<UserMeasurement> findByUserIdOrderByDateAsc(Long userId);
    Optional<UserMeasurement> findFirstByUserIdOrderByDateDesc(Long userId); // останнє значення
}

