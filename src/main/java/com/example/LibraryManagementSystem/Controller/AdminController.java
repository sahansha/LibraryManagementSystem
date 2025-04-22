package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.RegisterRequestDTO;
import com.example.LibraryManagementSystem.Model.ErrorDetails;
import com.example.LibraryManagementSystem.Model.User;
import com.example.LibraryManagementSystem.Service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Creating a new admin for Library Management System"
            ,summary = "Create Admin User")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created"
                    ),
                    @ApiResponse(
                            responseCode ="500",
                            description = "Internal Server Error",
                            content = @Content(
                                    schema =@Schema(
                                            implementation = ErrorDetails.class
                                    )
                            )
                    ),
            }
    )
    @PostMapping("/createadmin")
    public ResponseEntity<User> createdAdmin(@RequestBody RegisterRequestDTO registerRequestDTO)
    {
        User user=this.authenticationService.createAdmin(registerRequestDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
