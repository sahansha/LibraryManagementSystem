package com.example.LibraryManagementSystem.DAO;

import com.example.LibraryManagementSystem.Model.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRecordRepository extends JpaRepository<IssueRecord,Long> {
}
