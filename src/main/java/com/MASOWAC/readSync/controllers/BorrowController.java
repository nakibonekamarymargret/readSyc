package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.models.Borrow;
import com.MASOWAC.readSync.services.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    // Borrow a book
    @PostMapping("/borrow")
    public ResponseEntity<Borrow> borrowBook(@RequestParam Long readerId, @RequestParam Long bookId) {
        return ResponseEntity.ok(borrowService.borrowBook(readerId, bookId));
    }

    // Return a book
    @PutMapping("/return/{borrowId}")
    public ResponseEntity<Borrow> returnBook(@PathVariable Long borrowId) {
        return ResponseEntity.ok(borrowService.returnBook(borrowId));
    }

    // Get all books borrowed by a reader
    @GetMapping("/reader/{readerId}")
    public ResponseEntity<List<Borrow>> getBorrowedBooksByReader(@PathVariable Long readerId) {
        return ResponseEntity.ok(borrowService.getBorrowedBooksByReader(readerId));
    }

    // Get all readers who borrowed a book
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Borrow>> getReadersByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(borrowService.getReadersByBook(bookId));
    }
}
