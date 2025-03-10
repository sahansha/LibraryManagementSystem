package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DAO.BookRepository;
import com.example.LibraryManagementSystem.DAO.IssueRecordRepository;
import com.example.LibraryManagementSystem.DAO.UserRepository;
import com.example.LibraryManagementSystem.Exception.BookNotFoundException;
import com.example.LibraryManagementSystem.Exception.UserNotFoundException;
import com.example.LibraryManagementSystem.Model.Book;
import com.example.LibraryManagementSystem.Model.IssueRecord;
import com.example.LibraryManagementSystem.Model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IssueRecordService {

    private final BookRepository bookRepository;
    private final IssueRecordRepository issueRecordRepository;
    private final UserRepository userRepository;

    public IssueRecordService(BookRepository bookRepository, IssueRecordRepository issueRecordRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.issueRecordRepository = issueRecordRepository;
        this.userRepository = userRepository;
    }

    public IssueRecord issueBook(Long bookId) {
        try{
            Book book=this.bookRepository.findById(bookId)
                    .orElseThrow(()->new BookNotFoundException(String.format("Book not found with id %d ",bookId)));
            if(book.getQuantity()<=0|| !book.getIsAvailable())
            {
                throw new BookNotFoundException(String.format("Book with id %d is not available",bookId));
            }
            book.setQuantity(book.getQuantity()-1);
            if(book.getQuantity()==0)
            {
                book.setIsAvailable(false);
            }

            String userName= SecurityContextHolder.getContext().getAuthentication().getName();
            User user=this.userRepository.findByUsername(userName)
                    .orElseThrow(()-> new UserNotFoundException("User Not Found"));

            IssueRecord issueRecord=IssueRecord.builder()
                    .issueDate(LocalDate.now())
                    .dueDate(LocalDate.now().plusDays(14))
                    .book(book)
                    .user(user)
                    .build();
            this.bookRepository.save(book);
            return this.issueRecordRepository.save(issueRecord);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public IssueRecord returnBook(Long issuebookid) {
        try{
            IssueRecord issueRecord=this.issueRecordRepository.findById(issuebookid)
                    .orElseThrow(()->new RuntimeException("Issue Record not Found"));

            if(issueRecord.isReturned())
            {
                throw new RuntimeException("Book Already Returned");
            }
            Book book=issueRecord.getBook();
            book.setQuantity(book.getQuantity()+1);
            book.setIsAvailable(true);

            issueRecord.setReturnDate(LocalDate.now());
            issueRecord.setReturned(true);

            this.bookRepository.save(book);
            return this.issueRecordRepository.save(issueRecord);


        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
