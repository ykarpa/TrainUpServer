package com.ykarpa.TrainUp.controller;

import com.ykarpa.TrainUp.DTO.AuthResponseDTO;
import com.ykarpa.TrainUp.DTO.LoginDTO;
import com.ykarpa.TrainUp.DTO.RegistrationDTO;
import com.ykarpa.TrainUp.DTO.ChangePasswordDTO;
import com.ykarpa.TrainUp.entity.Role;
import com.ykarpa.TrainUp.entity.User;
import com.ykarpa.TrainUp.service.AuthService;
import com.ykarpa.TrainUp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegistrationDTO registrationDTO) {
        try {
            AuthResponseDTO response = authService.register(registrationDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto){
        try {
            AuthResponseDTO response = authService.login(loginDto);
            User user = userService.getUserByUsernameOrEmail(loginDto.getUsernameOrEmail());
            Set<Role> roles = user.getRoles();
            response.setRoles(roles);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            authService.changePassword(changePasswordDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.existsByUsername(username));
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.existsByEmail(email));
    }
}
