package com.example.LibraryManagementSystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO {
    @Min(value = 2,message = "Username length must be minimum 2")
    private String username;
    @Email(message = "Email Format is incorrect")
    @Min(value = 1,message = "Email is required")
    private String email;
    @Min(value = 1,message = "Password cannot be blank")
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
