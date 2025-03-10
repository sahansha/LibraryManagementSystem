package com.example.LibraryManagementSystem.DTO;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {

    @Min(value = 2,message = "Username length must be minimum 2")
    private String username;
    @Min(value = 1,message ="Password is required")
    private String password;

}
