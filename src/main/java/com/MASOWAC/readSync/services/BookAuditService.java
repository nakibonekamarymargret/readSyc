package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.models.BookAudit;
import com.MASOWAC.readSync.repository.BookAuditRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAuditService {

    private final BookAuditRepository bookAuditRepository;

    public BookAuditService(BookAuditRepository bookAuditRepository){
        this.bookAuditRepository = bookAuditRepository;
    }

    public List<BookAudit>getAllAuditsLogs(){
        return bookAuditRepository.findAll();
    }
}
