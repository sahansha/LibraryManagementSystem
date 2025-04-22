package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.LoginRequestDTO;
import com.example.LibraryManagementSystem.DTO.LoginResponseDTO;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(description = "Creating a new user for Library Management System"
            ,summary = "Create a User")
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
    @PostMapping("/createuser")
    public ResponseEntity<User> createNornamlUser(@RequestBody RegisterRequestDTO registerRequestDTO)
    {
        User user=this.authenticationService.createNormalUser(registerRequestDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Operation(description = "Login a user for Library Management System"
            ,summary = "Login")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
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
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO)
    {
        LoginResponseDTO loginResponseDTO=this.authenticationService.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }


}
