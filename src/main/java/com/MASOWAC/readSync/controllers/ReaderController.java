package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.dto.ReaderResponse;
import com.MASOWAC.readSync.models.Reader;
import com.MASOWAC.readSync.services.ReaderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/readers")
public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    //Get all Readers
    @GetMapping
    public Page<Reader> getAllReaders(
            @RequestParam(defaultValue="0")int page,
            @RequestParam(defaultValue="5")int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue="true")boolean ascending

    ) {
        Sort sort = ascending?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return readerService.getAllReaders(pageable);
    }
//Get readers by id
    @GetMapping("/reader/{id}")
    public Reader findById(@PathVariable Long id){
        return readerService.getById(id);
    }
    @GetMapping("/search")
    public List<Reader> searchReader(@RequestParam String field, @RequestParam String value) {
        return readerService.searchReaderByField(field, value);
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

    // Get readers sorted by the number of books borrowed with pagination


}
