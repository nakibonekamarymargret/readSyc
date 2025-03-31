package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.dto.BookDTO;
import com.MASOWAC.readSync.exceptions.NoBookFoundException;
import com.MASOWAC.readSync.exceptions.ReaderNotFoundException;
import com.MASOWAC.readSync.models.Book;
import com.MASOWAC.readSync.models.Publisher;
import com.MASOWAC.readSync.models.Reader;
import com.MASOWAC.readSync.repository.BookRepository;
import com.MASOWAC.readSync.repository.PublisherRepository;
import com.MASOWAC.readSync.repository.ReaderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final ReaderRepository readerRepository;


    public BookService(BookRepository bookRepository, PublisherRepository publisherRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.readerRepository = readerRepository;

    }

    //Creating a book
    public Book createBook(Book book) {
        if (book.getPublisher() == null || book.getPublisher().getId() == null) {
            throw new IllegalArgumentException("Publisher ID cannot be null");
        }

        Optional<Publisher> publisherOptional = publisherRepository.findById(book.getPublisher().getId());
        if (publisherOptional.isEmpty()) {
            throw new IllegalArgumentException("Publisher not found with ID: " + book.getPublisher().getId());
        }
        book.setPublisher(publisherOptional.get()); // Set the fetched Publisher object
        return bookRepository.save(book);
    }

    //Return all books
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    //Returning an optional/one id
    public Optional<Book> getBooKById(Long Id) {
        return bookRepository.findById(Id);
    }

    //    Updating and patching books
// Updating a book
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

                // Check if publisher is provided and update it
                if (bookDetails.getPublisher() != null && bookDetails.getPublisher().getId() != null) {
                    Optional<Publisher> publisherOptional = publisherRepository.findById(bookDetails.getPublisher().getId());
                    if (publisherOptional.isPresent()) {
                        book.setPublisher(publisherOptional.get()); // Set the fetched Publisher object
                    } else {
                        throw new IllegalArgumentException("Publisher not found with ID: " + bookDetails.getPublisher().getId());
                    }
                }

                // Save and return the updated book
                return bookRepository.save(book);
            }).orElseThrow(() -> new RuntimeException("Book not found"));
        } catch (Exception e) {
            // Handle the exception (log it, or rethrow as necessary)
            throw new RuntimeException("Error updating the book", e);
        }
    }

    //deleting a book
    public void deleteBook(Long Id) {
        bookRepository.deleteById(Id);
    }

    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBooksByPublisher(Long publisherId) {
        return bookRepository.findByPublisherId(publisherId);
    }
//Relationships getting books borrowed by readers

    public Reader borrowBook(Long readerId, Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoBookFoundException("Book not found"));
        Reader reader = readerRepository.findById(readerId).orElseThrow(() -> new ReaderNotFoundException("Reader not found"));
        if (reader.getBorrowedBooks().contains(book)) {
            throw new RuntimeException("Book is currently not available");
        }
        reader.getBorrowedBooks().add(book);
        return readerRepository.save(reader);
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public List<Book> getBookByPublishedYear(int publishedYear, Pageable pageable) {
        return bookRepository.findBookByPublishedYear(publishedYear, pageable);
    }

    public Page<BookDTO> getAllBooksWithoutPublisher(Pageable pageable) {
        return bookRepository.findAllBooksWithoutPublisher(pageable);
    }

    public List<BookDTO> getBooksByFilters(String title, String author, Integer publishedYear, Boolean available) {
        List<BookDTO> books = bookRepository.findBooksByFilters(title, author, publishedYear, available);
        if (books.isEmpty()) {
            List<String> filterConditions = new ArrayList<>();

            // Dynamically append filter conditions
            if (title != null && !title.isEmpty()) {
                filterConditions.add("with title '" + title + "'");
            }
            if (author != null && !author.isEmpty()) {
                filterConditions.add("by author '" + author + "'");
            }
            if (publishedYear != null) {
                filterConditions.add("for year " + publishedYear);
            }
            if (available != null) {
                filterConditions.add(available ? "that are available" : "that are not available");
            }

            // If no filter conditions were added, return a generic message
            String message = "Book not found";
            if (!filterConditions.isEmpty()) {
                message += " " + String.join(" ", filterConditions);
            }

            // Throw a custom exception with the generated message
            throw new NoBookFoundException(message);
        }

        return books;
    }

}