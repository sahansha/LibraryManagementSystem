package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Model.ErrorDetails;
import com.example.LibraryManagementSystem.Model.IssueRecord;
import com.example.LibraryManagementSystem.Service.IssueRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Issue Record",
        description = "The Issue Book Record API"
)
@RestController
@RequestMapping(path = "/issue-records")
public class IssueRecordController {

    private final IssueRecordService issueRecordService;

    public IssueRecordController(IssueRecordService issueRecordService) {
        this.issueRecordService = issueRecordService;
    }

    @Operation(description = "Issuing a book to a user"
            ,summary = "Issue a Book")
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
    @PostMapping("/issuebook/{id}")
    public ResponseEntity<IssueRecord> issueBook(@PathVariable Long id)
    {
        IssueRecord issueRecord=this.issueRecordService.issueBook(id);
        return new ResponseEntity<>(issueRecord, HttpStatus.CREATED);
    }

    @Operation(description = "Returning of a book back to Library"
            ,summary = "Return a Book")
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
    @PostMapping("/returnbook/{issuebookid}")
    public ResponseEntity<IssueRecord> returnBook(@PathVariable Long issuebookid)
    {
        IssueRecord issueRecord=this.issueRecordService.returnBook(issuebookid);
        return new ResponseEntity<>(issueRecord, HttpStatus.CREATED);
    }
}
