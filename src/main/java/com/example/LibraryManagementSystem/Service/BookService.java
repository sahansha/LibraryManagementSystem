package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DAO.BookRepository;
import com.example.LibraryManagementSystem.DTO.BookDTO;
import com.example.LibraryManagementSystem.Exception.BookNotFoundException;
import com.example.LibraryManagementSystem.Model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        try{
            List<Book> books=bookRepository.findAll();
            return books;
        }
        catch (RuntimeException ex)
        {
            throw new RuntimeException(ex);
        }
    }


    public Book getBookById(Long id) {

        try {
            return this.bookRepository.findById(id)
                    .orElseThrow(()->new BookNotFoundException(String.format("Book not found with id %d ",id)));
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public Book addNewBook(BookDTO bookDTO) {
        try {
            Book book=Book.builder()
                    .title(bookDTO.getTitle())
                    .author(bookDTO.getAuthor())
                    .isbn(bookDTO.getIsbn())
                    .quantity(bookDTO.getQuantity())
                    .isAvailable(bookDTO.getIsAvailable())
                    .build();
            return this.bookRepository.save(book);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public Book updateBook(long id, BookDTO bookDTO) {
        try{
            Book oldBook=this.bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(String.format("Book not found with id %d ",id)));
            oldBook.setIsbn(bookDTO.getIsbn());
            oldBook.setTitle(bookDTO.getTitle());
            oldBook.setAuthor(bookDTO.getAuthor());
            oldBook.setQuantity(bookDTO.getQuantity());
            oldBook.setIsAvailable(bookDTO.getIsAvailable());
            return this.bookRepository.save(oldBook);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public void deleteBookById(long id) {
        try{
            this.bookRepository.deleteById(id);
        }catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
