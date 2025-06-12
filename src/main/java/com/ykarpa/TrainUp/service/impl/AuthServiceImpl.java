package com.ykarpa.TrainUp.service.impl;
import com.ykarpa.TrainUp.DTO.AuthResponseDTO;
import com.ykarpa.TrainUp.DTO.LoginDTO;
import com.ykarpa.TrainUp.DTO.RegistrationDTO;
import com.ykarpa.TrainUp.DTO.ChangePasswordDTO;
import com.ykarpa.TrainUp.config.JwtTokenProvider;
import com.ykarpa.TrainUp.entity.Role;
import com.ykarpa.TrainUp.entity.User;
import com.ykarpa.TrainUp.repository.UserRepository;
import com.ykarpa.TrainUp.repository.RoleRepository;
import com.ykarpa.TrainUp.service.AuthService;
import com.ykarpa.TrainUp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;
//    private RoleRepository roleRepository;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Override
    public AuthResponseDTO login(LoginDTO loginDto) {
        try {
            Optional<User> userOpt = userRepository.findByUsername(loginDto.getUsernameOrEmail())
                    .or(() -> userRepository.findByEmail(loginDto.getUsernameOrEmail()));

            User user = userOpt.orElseThrow(() -> new RuntimeException("User not found"));

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            loginDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(user);

            return AuthResponseDTO.builder()
                    .token(token)
                    .userId(user.getId())
                    .build();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AuthResponseDTO register(RegistrationDTO registrationDTO) {
        try {
            if (userRepository.existsByEmail(registrationDTO.getEmail())) {
                throw new RuntimeException("Email already in use");
            }
            if (userRepository.existsByUsername(registrationDTO.getUsername())) {
                throw new RuntimeException("Username already in use");
            }

            Role userRole = roleRepository.findByName("CLIENT")
                    .orElseThrow(() -> new RuntimeException("Role CLIENT not found"));

            User user = mapRegisterDtoToUser(registrationDTO);
            user.setRoles(Set.of(userRole));

            String encodedPassword = passwordEncoder.encode(registrationDTO.getPassword());
            user.setPassword(encodedPassword);

            userRepository.save(user);

            String token = jwtTokenProvider.generateToken(user);

            return AuthResponseDTO.builder()
                    .token(token)
                    .userId(user.getId())
                    .build();
        } catch (Exception e) {
            throw e;
        }
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        User user = userService.getUserById(changePasswordDTO.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userService.updateUser(user.getId(), user);
    }

    public boolean verifyPassword(Long userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return passwordEncoder.matches(password, user.getPassword());
    }

    private User mapRegisterDtoToUser(RegistrationDTO registrationDTO) {
        return User.builder()
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .username(registrationDTO.getUsername())
                .email(registrationDTO.getEmail())
                .password(registrationDTO.getPassword())
                .dateOfBirth(registrationDTO.getDateOfBirth())
                .roles(new HashSet<>())
                .build();
    }
}
