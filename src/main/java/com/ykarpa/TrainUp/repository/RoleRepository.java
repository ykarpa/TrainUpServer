package com.ykarpa.TrainUp.repository;

import com.ykarpa.TrainUp.entity.Role;
import com.ykarpa.TrainUp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String user);
}
