package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.models.BookAudit;
import com.MASOWAC.readSync.services.BookAuditService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book-audits")
public class BookAuditController {

    private final BookAuditService bookAuditService;

    public BookAuditController(BookAuditService bookAuditService){
        this.bookAuditService=bookAuditService;
    }

    @GetMapping
    List<BookAudit> getAllAuditsLogs(){
        return bookAuditService.getAllAuditsLogs();
    }
}
