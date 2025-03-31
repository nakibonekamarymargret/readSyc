package com.MASOWAC.readSync.services;


import com.MASOWAC.readSync.models.Book;
import com.MASOWAC.readSync.models.Reader;
import com.MASOWAC.readSync.models.Staff;
import com.MASOWAC.readSync.repository.BookRepository;
import com.MASOWAC.readSync.repository.ReaderRepository;
import com.MASOWAC.readSync.repository.StaffRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StaffService {
    private final StaffRepository staffRepository;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;


    public StaffService(StaffRepository staffRepository, ReaderRepository readerRepository, BookRepository bookRepository) {
        this.staffRepository = staffRepository;
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;

    }

    //    Applying CRUD
//    Creating a Staff
    public Staff createStaff(@Valid Staff staff) {
        return staffRepository.save(staff);
    }

    //    Returning all staff members using pagination and sorting
    public Page<Staff> getAllStaff(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    //    Returning staff by id
    public Optional<Staff> getStaffById(Long id) {
        return staffRepository.findById(id);
    }

    //    Updating the staff
    public Staff updateStaff(Long id, Staff staffDetails) {
        return staffRepository.findById(id).map(staff -> {
            // Copy properties from staffDetails to staff (ignores null values and doesn't copy the id)
            BeanUtils.copyProperties(staffDetails, staff, "id");
            return staffRepository.save(staff);
        }).orElseThrow(() -> new RuntimeException("Staff not found with id " + id));
    }

    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    // manage Books
//    public Staff manageBooks(Staff staffBook) {
//        if (staffBook.getBooks() == null || staffBook.getBooks().isEmpty()) {
//            throw new IllegalArgumentException("Book set cannot be null or empty");
//        }
//
//        // Validate each book in the set
//        for (Book book : staffBook.getBooks()) {
//            if (book.getId() == null) {
//                throw new IllegalArgumentException("Book ID cannot be null");
//            }
//
//            Optional<Book> bookOptional = bookRepository.findById(book.getId());
//            if (bookOptional.isEmpty()) {
//                throw new IllegalArgumentException("Book not found with ID: " + book.getId());
//            }
//
//            // Update the relationship if needed
//            book.setStaff(staffBook);
//        }
//
//        // Save the staff entity with the updated book relationships
//        return staffRepository.save(staffBook);
//    }

    //Track reader
    public Staff assignReaderToStaff(Long staffId, Long readerId) {
        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new RuntimeException("Staff not found"));
        Reader reader = readerRepository.findById(readerId).orElseThrow(() -> new RuntimeException("Reader not found"));

        // Add the reader to the staff's readers list
        staff.getReaders().add(reader);
        // Add the staff to the reader's staff list
        reader.getStaff().add(staff);

        // Save the updated staff and reader
        staffRepository.save(staff);
        readerRepository.save(reader);

        return staff;
    }

    // Method to get a staff with their readers
    public Staff getStaffWithReaders(Long staffId) {
        return staffRepository.findById(staffId)
                .map(staff -> {
                    staff.getReaders().size();  // This will initialize the collection and load the readers
                    return staff;
                })
                .orElseThrow(() -> new RuntimeException("Staff not found"));
    }

    public Staff manageBooks(String bookTitle, String staffName) {
        // Find the staff member by name
        Staff staff = staffRepository.findByFullName(staffName)
                .orElseThrow(() -> new IllegalArgumentException("Staff member '" + staffName + "' not found"));

        // Find the book by title
        Book book = bookRepository.findByTitle(bookTitle)
                .orElseThrow(() -> new IllegalArgumentException("Book titled '" + bookTitle + "' not found"));

        // Initialize the book set if null
        if (staff.getBooks() == null) {
            staff.setBooks(new HashSet<>());
        }

        // Check if the book is already assigned
        if (staff.getBooks().contains(book)) {
            throw new IllegalArgumentException("Book '" + bookTitle + "' is already assigned to '" + staffName + "'");
        }

        // Assign the book to the staff member
        staff.getBooks().add(book);
        book.setStaff(staff); // Update the relationship on the book side

        // Save and return the updated staff entity
        return staffRepository.save(staff);
    }

}
