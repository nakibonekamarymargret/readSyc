package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.dto.ReaderResponse;
import com.MASOWAC.readSync.exceptions.ReaderNotFoundException;
import com.MASOWAC.readSync.models.Reader;
import com.MASOWAC.readSync.services.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/readers")
public class ReaderController {
    private final ReaderService readerService;
    private HashMap<String, Object> response;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    //Get all Readers
    @GetMapping
    public List<Reader> getAllReaders() {
        return readerService.findAllReaders();
    }

    //    Get reader by id
    @GetMapping("/{id}")
    public Reader getReader(@PathVariable Long id) {
        return readerService.getReader(id);
    }

    //create reader
    @PostMapping("/add-reader")
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        return ResponseEntity.ok(readerService.createReader(reader));
    }

    //Update reader
    @PutMapping("/edit_reader")
    public ResponseEntity<Reader> updateReader(@PathVariable Long id, @RequestBody Reader readerDetails) {
        return ResponseEntity.ok(readerService.updateReader(id, readerDetails));
    }

    //    Delete reader
    public ResponseEntity<Reader> deleteReader(@PathVariable Long id) {
        Reader deletedReader = readerService.deleteReader(id);
        return ResponseEntity.ok(deletedReader);
    }

//    Borrowing a book
    @PutMapping("/{id}/borrow")
    public ResponseEntity<Reader> borrowBooks(@PathVariable Long id, @RequestBody List<Long> bookIds){
        Reader updatedReader = readerService.borrowBooks(id, bookIds);
        return ResponseEntity.ok(updatedReader);
    }

}
