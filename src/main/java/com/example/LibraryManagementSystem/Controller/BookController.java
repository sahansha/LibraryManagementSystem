package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.BookDTO;
import com.example.LibraryManagementSystem.Model.Book;
import com.example.LibraryManagementSystem.Model.ErrorDetails;
import com.example.LibraryManagementSystem.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@Tag(name = "Book", description = "Book API")
@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(description = "Get the details of all the books in library"
            ,summary = "Get all books")
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
                    )
            }
    )
    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @Operation(description = "Get the details of all a book by its id"
            ,summary = "Get single book")
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
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @Operation(description = "Add a new book to library"
            ,summary = "New Book")
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
    @PostMapping("/addBook")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> addNewBook(@RequestBody BookDTO bookDTO)
    {
        Book savedUser= bookService.addNewBook(bookDTO);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @Operation(description = "Update an existing book to library"
            ,summary = "Updating a Book")
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
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable long id,@RequestBody BookDTO bookDTO)
    {
        return ResponseEntity.ok(bookService.updateBook(id,bookDTO));
    }

    @Operation(description = "Deleting a book by it's id"
            ,summary = "Delete a Book")
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
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBookById(@PathVariable long id)
    {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
