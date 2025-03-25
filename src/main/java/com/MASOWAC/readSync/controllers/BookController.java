package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.dto.BookDTO;
import com.MASOWAC.readSync.models.Book;
import com.MASOWAC.readSync.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //    Get all books
    @GetMapping
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    //Get one add
    @GetMapping("/{Id}")
    public ResponseEntity<Map<String, Object>> getBooKById(@PathVariable Long Id) {
        Optional<Book> book = bookService.getBooKById(Id);
        Map<String, Object> response = new HashMap<>();
        if (book.isPresent()) {
            response.put("statusCode", HttpStatus.OK.value());
            response.put("message", "Book returned is " + book.get().getTitle());
            response.put("data", book.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("statusCode", HttpStatus.NOT_FOUND.value());
            response.put("message", "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    //    Get book by title
    @GetMapping("/title/{title}")
    public ResponseEntity<Map<String, Object>> getBookByTitle(@PathVariable String title) {
        Optional<Book> book = bookService.getBookByTitle(title);
        Map<String, Object> response = new HashMap<>();
        if (book.isPresent()) {
            response.put("status", "success");
            response.put("message", "Book found");
            response.put("data", book.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failure");
            response.put("message", "Book with title '" + title + "' not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    //Get sorted books
    @GetMapping("/sort")
    public Page<Book> getSortedBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookService.findAll(pageable);
    }

    //Get books with no publishers
    @GetMapping("/no-publisher")
    public Page<BookDTO> getAllBooksWithoutPublisher(Pageable pageable) {
        return bookService.getAllBooksWithoutPublisher(pageable);
    }

    //    Get Books by filters
    @GetMapping("/search")
    public List<BookDTO> getFilteredBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer publishedYear,
            @RequestParam(required = false) Boolean available) {
        return bookService.getBooksByFilters(title, author, publishedYear, available);
    }


    //    Create books
    @PostMapping("/add-book")
    public ResponseEntity<Map<String, Object>> createBook(@RequestBody Book book) {
        Map<String, Object> response = new HashMap<>();
        try {
            Book createdBook = bookService.createBook(book);
            response.put("status", "success");
            response.put("message", "Book created successfully");
            response.put("data", createdBook);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", "Failed to create book");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    //Update book with put and patch
    @PutMapping("/update-book/{Id}")
    public ResponseEntity<Map<String, Object>> updateBook(@PathVariable Long Id, @RequestBody Book bookDetails) {
        Map<String, Object> response = new HashMap<>();
        try {
            Book updatedBook = bookService.updateBook(Id, bookDetails);
            response.put("status", "success");
            response.put("message", "Book updated successfully");
            response.put("data", updatedBook);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", "Failed to update book");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    //    public Book updateBook(@PathVariable Long Id,  @RequestBody Book bookDetails){
//        return bookService.updateBook(Id,bookDetails);
//    }
//    Using patch
    @PatchMapping("/update-book/{Id}")
    public ResponseEntity<Map<String, Object>> patchBook(@PathVariable Long Id, @RequestBody Book bookDetails) {
        Map<String, Object> response = new HashMap<>();
        try {
            Book updatedBook = bookService.updateBook(Id, bookDetails);
            response.put("status", "success");
            response.put("message", "Book updated successfully");
            response.put("data", updatedBook);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", "Failed to update book");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    //    public Book patchBook(@PathVariable Long Id, @RequestBody Book bookDetails){
//        return bookService.updateBook(Id, bookDetails);
//    }
    //Delete  a book
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long Id) {
        Map<String, Object> response = new HashMap<>();

        try {
            bookService.deleteBook(Id);
            response.put("status", "success");
            response.put("message", "Book deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", "Failed to delete book");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/publisher/{publisherId}")
    public ResponseEntity<List<Book>> getBooksByPublisher(@PathVariable Long publisherId) {
        return ResponseEntity.ok(bookService.getBooksByPublisher(publisherId));
    }
//        public void deleteBook(@PathVariable Long Id){
//        bookService.deleteBook(Id);
//    }
}

