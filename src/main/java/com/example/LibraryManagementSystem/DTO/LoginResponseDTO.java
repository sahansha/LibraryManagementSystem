package com.example.LibraryManagementSystem.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "LoginResponse",
        description = "Login Response Data Transfer Object"
)
public class LoginResponseDTO {
    @Schema(description = "Bearer Token")
    private String token;
    @Schema(description = "Username", example = "admin")
    private String username;
    @Schema(description = "User Roles")
    private Set<String> roles;

}
