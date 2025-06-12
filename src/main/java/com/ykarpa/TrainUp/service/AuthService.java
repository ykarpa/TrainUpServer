package com.ykarpa.TrainUp.service;


import com.ykarpa.TrainUp.DTO.AuthResponseDTO;
import com.ykarpa.TrainUp.DTO.ChangePasswordDTO;
import com.ykarpa.TrainUp.DTO.LoginDTO;
import com.ykarpa.TrainUp.DTO.RegistrationDTO;

public interface AuthService {
    AuthResponseDTO login(LoginDTO loginDto);
    AuthResponseDTO register(RegistrationDTO registerDto);
    void changePassword(ChangePasswordDTO changePasswordDTO);
    boolean verifyPassword(Long userId, String password);
}
