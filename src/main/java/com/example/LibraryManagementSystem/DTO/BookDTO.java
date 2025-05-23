package com.example.LibraryManagementSystem.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
    name = "Book DTO",
    description = "Book Data Transfer Object"
)
public class BookDTO {
    @Schema(description = "Book Title", example = "my lifespan")
    private String title;

    @Schema(description = "Book Author", example = "JK Rowling")
    private String author;

    @Schema(description = "Book ISBN", example = "123456789")
    private String isbn;

    @Schema(description = "Book Quantity", example = "5")
    private Integer quantity;

    @Schema(description = "Book Availability", example = "true")
    private Boolean isAvailable;
}
