package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.RegisterRequestDTO;
import com.example.LibraryManagementSystem.Model.User;
import com.example.LibraryManagementSystem.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AuthenticationService authenticationService;

    public AdminController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/createadmin")
    public ResponseEntity<User> createdAdmin(@RequestBody RegisterRequestDTO registerRequestDTO)
    {
        User user=this.authenticationService.createAdmin(registerRequestDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
