package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.dto.ReaderResponse;
import com.MASOWAC.readSync.models.Reader;
import com.MASOWAC.readSync.services.ReaderService;
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
public List<ReaderResponse> getAllReaders() {
    return readerService.getAllReaders();
}
    @GetMapping("/search")
    public List<Reader> searchReader(@RequestParam String field, @RequestParam String value) {
        return readerService.searchReaderByField(field, value);
    }
    //create reader
    @PostMapping("/add-reader")
    public ResponseEntity<Reader>createReader(@RequestBody Reader reader){
        return ResponseEntity.ok(readerService.createReader(reader));
    }
//Update reader
    @PutMapping("/edit_reader")
    public ResponseEntity<Reader>updateReader(@PathVariable Long id, @RequestBody Reader readerDetails){
        return ResponseEntity.ok(readerService.updateReader(id,readerDetails));
    }

//    Delete reader
public ResponseEntity<Reader> deleteReader(@PathVariable Long id) {
    Reader deletedReader = readerService.deleteReader(id);
    return ResponseEntity.ok(deletedReader);
}

}
