package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.models.Book;
import com.MASOWAC.readSync.services.BookService;
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

    public BookController(BookService bookService){
        this.bookService= bookService;
    }
//    Get all books
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
//
//    Modifying the response entity to use the ResponseEntity<>
//    @GetMapping
//    public ResponseEntity<Map<String,Object>>getAllBooks(){
//        List<Book>books = bookService.getAllBooks();
//        Map<String,Object> response = new HashMap<>();
//        if(books.isEmpty()){
//            response.put("status","failure");
//            response.put("message","No book Available");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }else{
//            response.put("statusCode", HttpStatus.OK.value());
//            response.put("status","success");
//            response.put("message","Books successfully returned");
//            response.put("data","books");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//
//        }
//
//    }
//Get one book
    @GetMapping("/{Id}")
    public ResponseEntity<Map<String,Object>>getBooKById(@PathVariable Long Id){
        Optional<Book> book= bookService.getBooKById(Id);
        Map<String, Object> response = new HashMap<>();
        if(book.isPresent()){
            response.put("statusCode",HttpStatus.OK.value());
            response.put("message","Book returned is "+book.get().getTitle());
            response.put("data", book.get());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else {
            response.put("statusCode", HttpStatus.NOT_FOUND.value());
            response.put("message", "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
//    Get book by title
    @GetMapping("/{title}")
    public ResponseEntity<Map<String,Object>>grtBookByTitle(@PathVariable String title){
        Optional<Book> book = bookService.getBookByTitle(title);
        Map<String,Object>response =new HashMap<>();
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

//    public Optional<Book> getBookByTitle(@PathVariable String title){
//        return bookService.getBookByTitle(title);
//    }

//    Create books
    @PostMapping("/add-book")
    public ResponseEntity<Map<String, Object>>createBook(@RequestBody Book book){
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

//    public Book createBook(@RequestBody Book book){
//        return bookService.createBook(book);
//    }
//Update book with put and patch
    @PutMapping("/update-book/{Id}")
    public ResponseEntity<Map<String, Object>>updateBook(@PathVariable Long Id,  @RequestBody Book bookDetails){
        Map<String, Object>response = new HashMap<>();
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
        Map<String, Object>response = new HashMap<>();
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
//        public void deleteBook(@PathVariable Long Id){
//        bookService.deleteBook(Id);
//    }
}

