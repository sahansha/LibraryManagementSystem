package com.example.LibraryManagementSystem.DAO;

import com.example.LibraryManagementSystem.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
