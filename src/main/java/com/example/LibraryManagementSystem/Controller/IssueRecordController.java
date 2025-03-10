package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Model.IssueRecord;
import com.example.LibraryManagementSystem.Service.IssueRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/issue-records")
public class IssueRecordController {

    private final IssueRecordService issueRecordService;

    public IssueRecordController(IssueRecordService issueRecordService) {
        this.issueRecordService = issueRecordService;
    }

    @PostMapping("/issuebook/{id}")
    public ResponseEntity<IssueRecord> issueBook(@PathVariable Long id)
    {
        IssueRecord issueRecord=this.issueRecordService.issueBook(id);
        return new ResponseEntity<>(issueRecord, HttpStatus.CREATED);
    }

    @PostMapping("/returnbook/{issuebookid}")
    public ResponseEntity<IssueRecord> returnBook(@PathVariable Long issuebookid)
    {
        IssueRecord issueRecord=this.issueRecordService.returnBook(issuebookid);
        return new ResponseEntity<>(issueRecord, HttpStatus.CREATED);
    }
}
