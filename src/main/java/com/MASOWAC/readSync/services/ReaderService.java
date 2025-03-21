package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.dto.ReaderResponse;
import com.MASOWAC.readSync.exceptions.NoBookFoundException;
import com.MASOWAC.readSync.exceptions.ReaderNotFoundException;
import com.MASOWAC.readSync.models.Book;
import com.MASOWAC.readSync.models.Reader;
import com.MASOWAC.readSync.repository.BookRepository;
import com.MASOWAC.readSync.repository.ReaderRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    private HashMap<String, Object> response;

    public ReaderService(ReaderRepository readerRepository, BookRepository bookRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;

    }

    //CRUD Part
//    Creating a reader
    public Reader createReader(Reader reader) {
        return readerRepository.save(reader);
    }

    //    Return all users
    public List<Reader> findAllReaders() {
        return readerRepository.findAll();
    }

    //Find reader by id
    public Reader getReader(Long id) {
        return readerRepository.findById(id).orElseThrow(
                () -> new ReaderNotFoundException("No reader found with id: " + id)
        );
    }

    public Reader updateReader(Long id, Reader readerDetails) {
        try {
            return readerRepository.findById(id).map(reader -> {
//                Update fields if not null
                if (readerDetails.getFirstName() != null) {
                    reader.setFirstName(readerDetails.getFirstName());
                }
                if (readerDetails.getLastName() != null) {
                    reader.setLastName(readerDetails.getLastName());
                }
                if (readerDetails.getEmail() != null) {
                    reader.setEmail(readerDetails.getEmail());
                }
                if (readerDetails.getAddress() != null) {
                    reader.setAddress(readerDetails.getAddress());
                }
                if (readerDetails.getPhoneNumber() != null) {
                    reader.setPhoneNumber(readerDetails.getPhoneNumber());
                }
                return readerRepository.save(reader);
            }).orElseThrow(() -> new RuntimeException("Reader not available"));
        } catch (Exception e) {
            throw new RuntimeException("Error updating the reader details", e);
        }

    }

    //Deleting a reader
    public Reader deleteReader(Long id) {
        Optional<Reader> reader = readerRepository.findById(id);
        if (reader.isPresent()) {
            readerRepository.deleteById(id);
            return reader.get(); // Return the deleted Reader object
        }
    else {
        throw new ReaderNotFoundException("Reader not found with id " + id);
    }

    }

//    Borrowing a book
@Transactional
public Reader borrowBooks(Long id, List<Long> bookIds) {
    Reader reader = readerRepository.findById(id)
            .orElseThrow(() -> new ReaderNotFoundException("Reader not found with ID: " + id));

    Set<Book> booksToBorrow = new HashSet<>(bookRepository.findAllById(bookIds));

    for (Book book : booksToBorrow) {
        if (!book.getAvailable()) {
            throw new NoBookFoundException("Book \""+book.getTitle()+" \" is currently out of stock.");
        }
        book.setAvailable(false); // Mark as borrowed
    }

    reader.getBorrowedBooks().addAll(booksToBorrow);
    return readerRepository.save(reader);
}

}
