package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DAO.UserRepository;
import com.example.LibraryManagementSystem.DTO.LoginRequestDTO;
import com.example.LibraryManagementSystem.DTO.LoginResponseDTO;
import com.example.LibraryManagementSystem.DTO.RegisterRequestDTO;
import com.example.LibraryManagementSystem.Model.User;
import com.example.LibraryManagementSystem.Security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User createNormalUser(RegisterRequestDTO registerRequestDTO) {
        try{
            if(this.userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent())
            {
                throw new RuntimeException("User already exits");
            }
            Set<String> roles=new HashSet<>();
            roles.add("ROLE_USER");
            User user=User.builder()
                    .email(registerRequestDTO.getEmail())
                    .username(registerRequestDTO.getUsername())
                    .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                    .roles(roles)
                    .build();
            return this.userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User createAdmin(@Valid RegisterRequestDTO registerRequestDTO) {
        if(this.userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent())
        {
            throw new RuntimeException("User already exits");
        }
        Set<String> roles=new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");

        User user=User.builder()
                .email(registerRequestDTO.getEmail())
                .username(registerRequestDTO.getUsername())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .roles(roles)
                .build();
        return this.userRepository.save(user);
    }

    public LoginResponseDTO login(@Valid LoginRequestDTO loginRequestDTO) {
        try{
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword())
            );
            String token=jwtService.generateToken(loginRequestDTO.getUsername());
            return LoginResponseDTO.builder()
                    .username(loginRequestDTO.getUsername())
                    .token(token)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
