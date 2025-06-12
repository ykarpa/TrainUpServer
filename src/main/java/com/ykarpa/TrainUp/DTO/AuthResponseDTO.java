package com.ykarpa.TrainUp.DTO;

import com.ykarpa.TrainUp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO{
    private String token;
    private Long userId;
    private Set<Role> roles;
}
