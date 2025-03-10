package com.example.LibraryManagementSystem.Model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private Instant timeStamp;
    private String message;
    private String description;
}
