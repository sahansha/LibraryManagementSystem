package com.example.LibraryManagementSystem.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private String title;
    private String author;
    private String isbn;
    private Integer quantity;
    private Boolean isAvailable;
}
