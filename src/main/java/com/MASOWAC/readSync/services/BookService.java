package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.exceptions.NoBookFoundException;
import com.MASOWAC.readSync.models.Book;
import com.MASOWAC.readSync.models.Publisher;
import com.MASOWAC.readSync.repository.BookRepository;
import com.MASOWAC.readSync.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, PublisherRepository publisherRepository){
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;

    }
    //Creating a book
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }


    //Return all books
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    //Returning an optional/one id
    public Book getBooKById(Long id) throws NoBookFoundException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoBookFoundException("Book not found with Id: " + id));
    }

//    Sorting and pagination
//    public Page<Book>findAll(Pageable pageable){
//        return bookRepository.findAll(pageable);
//    }
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable); // This returns a paginated result
    }
    //    Updating and patching books
    public Book updateBook(Long Id, Book bookDetails) {
        try {
        return bookRepository.findById(Id).map(book -> {
            // Update fields if not null
            if (bookDetails.getTitle() != null) {
                book.setTitle(bookDetails.getTitle());
            }
            if (bookDetails.getAuthor() != null) {
                book.setAuthor(bookDetails.getAuthor());
            }
            if (bookDetails.getIsbn() != null) {
                book.setIsbn(bookDetails.getIsbn());
            }
            if (bookDetails.getPublishedYear() > 0) {
                book.setPublishedYear(bookDetails.getPublishedYear());
            }
            // Save and return the updated book
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    } catch (Exception e) {
        // Handle the exception (log it, or rethrow as necessary)
        throw new RuntimeException("Error updating the book", e);
    }
}

//    public Book updateUser(Long id, Book bookDetails) {
//        return bookRepository.findById(id).map(book -> {
//                    // Map non-null properties from userDetails to user
//                    modelMapper.map(bookDetails, book);
//                    return bookRepository.save(book);
//                })
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//deleting a book
    public void deleteBook(Long Id){
        bookRepository.deleteById(Id);
    }

    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
    public List<Book> getBooksByPublisher(Long publisherId) {
        return bookRepository.findByPublisherId(publisherId);
    }

//    Delete student by id






}