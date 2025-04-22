package com.example.LibraryManagementSystem.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
    description = "Registration Request",
        name = "RegisterRequest"
)
public class RegisterRequestDTO {
    @Min(value = 2,message = "Username length must be minimum 2")
    @Schema(description = "Username", example = "admin")
    private String username;
    @Email(message = "Email Format is incorrect")
    @Min(value = 1,message = "Email is required")
    @Schema(description = "Email", example = "admin@gmail.com")
    private String email;
    @Min(value = 1,message = "Password cannot be blank")
    @Schema(description = "Password")
    private String password;

    public @Min(value = 2, message = "Username length must be minimum 2") String getUsername() {
        return username;
    }

    public void setUsername(@Min(value = 2, message = "Username length must be minimum 2") String username) {
        this.username = username;
    }

    public @Email(message = "Email Format is incorrect") @Min(value = 1, message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email Format is incorrect") @Min(value = 1, message = "Email is required") String email) {
        this.email = email;
    }

    public @Min(value = 1, message = "Password cannot be blank") String getPassword() {
        return password;
    }

    public void setPassword(@Min(value = 1, message = "Password cannot be blank") String password) {
        this.password = password;
    }
}
