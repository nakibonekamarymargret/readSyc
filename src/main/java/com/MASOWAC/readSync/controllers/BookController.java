package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.exceptions.NoBookFoundException;
import com.MASOWAC.readSync.models.Book;
import com.MASOWAC.readSync.services.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private HashMap<String , Object> response;
    public BookController(BookService bookService){
        this.bookService= bookService;
        this.response = new HashMap<>();
    }
//    Get all books

//    Modifying the response entity to use the ResponseEntity<>
    @GetMapping
    public List<Book>getAllBooks(){
        return bookService.getAllBooks();
    }
//Get one book
@GetMapping("/{Id}")
public ResponseEntity<HashMap<String, Object>> getBookById(@PathVariable Long Id, HttpServletRequest request) {
    try {
        Book book = bookService.getBooKById(Id); // This may throw NoBookFoundException
        response.put("statusCode", HttpStatus.OK.value());
        response.put("status", "success");
        response.put("message", "Book successfully retrieved");
        response.put("data", book);
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (NoBookFoundException e) {
        throw e;
    }
}
//
//    Retrieving paginated and sorted books
    @GetMapping("/sort")
    public Page<Book>getAllBooks(
            @RequestParam (defaultValue ="0") int page,
            @RequestParam (defaultValue ="5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam (defaultValue ="true") boolean ascending
            ){

            Sort sort = ascending?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
            Pageable pageable = PageRequest.of(page, size, sort);
            return bookService.findAll(pageable);


    }
//   Create books
    @PostMapping("/add-book")
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book){
        return ResponseEntity.ok(bookService.createBook(book));
    }
//    public Book createBook(@RequestBody Book book){
//        return bookService.createBook(book);
//    }
//Update book with put and patch
    @PutMapping("/update-book/{Id}")
    public ResponseEntity<Map<String, Object>>updateBook(@PathVariable Long Id,  @RequestBody Book bookDetails){
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
    public ResponseEntity<Map<String, Object>>patchBook(@PathVariable Long Id,  @RequestBody Book bookDetails){
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

