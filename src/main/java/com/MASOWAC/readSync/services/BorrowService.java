package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.exceptions.ResourceNotFoundException;
import com.MASOWAC.readSync.models.Borrow;
import com.MASOWAC.readSync.models.Reader;
import com.MASOWAC.readSync.models.Book;
import com.MASOWAC.readSync.repository.BorrowRepository;
import com.MASOWAC.readSync.repository.ReaderRepository;
import com.MASOWAC.readSync.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    public BorrowService(BorrowRepository borrowRepository, ReaderRepository readerRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
    }

    // Borrow a book
    public Borrow borrowBook(Long readerId, Long bookId) {
        Optional<Reader> readerOpt = readerRepository.findById(readerId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (readerOpt.isPresent() && bookOpt.isPresent()) {
            Borrow borrow = new Borrow(readerOpt.get(), bookOpt.get(), LocalDateTime.now(), null);
            return borrowRepository.save(borrow);
        }
        throw new RuntimeException("Reader or Book not found!");
    }

    // Return a book (update return date)
    public Borrow returnBook(Long borrowId) {
        Optional<Borrow> borrowOpt = borrowRepository.findById(borrowId);
        if (borrowOpt.isPresent()) {
            Borrow borrow = borrowOpt.get();
            borrow.setReturnDate(LocalDateTime.now());
            return borrowRepository.save(borrow);
        }
        throw new RuntimeException("Borrow record not found!");
    }

    // Get all books borrowed by a reader
    public List<Borrow> getBorrowedBooksByReader(Long readerId) {
        return borrowRepository.findByReaderId(readerId);
    }

    // Get all readers who borrowed a specific book
    public List<Borrow> getReadersByBook(Long bookId) {
        return borrowRepository.findByBookId(bookId);
    }

    @Transactional
    public Borrow updateBorrowedBook(Long id, Borrow updatedBorrow) {
        Optional<Borrow> existingBorrowedBook = borrowRepository.findById(id);

        if (existingBorrowedBook.isPresent()) {
            Borrow existingEmployee = existingBorrowedBook.get();
            existingEmployee.setReader(updatedBorrow.getReader());
            existingEmployee.setBook(updatedBorrow.getBook());
            existingEmployee.setBorrowDate(updatedBorrow.getBorrowDate());
            existingEmployee.setReturnDate(updatedBorrow.getReturnDate());
            return borrowRepository.save(existingEmployee);
        } else {
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
    }
}

