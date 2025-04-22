package com.example.LibraryManagementSystem.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Login Request Object",
name = "LoginRequest")
public class LoginRequestDTO {

    @Min(value = 2,message = "Username length must be minimum 2")
    @Schema(
            description = "Username",
            example = "admin"
    )
    private String username;
    @Min(value = 1,message ="Password is required")
    @Schema(
            description = "Password"
    )
    private String password;

}
