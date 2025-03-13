package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.enums.ManageOperation;
import com.MASOWAC.readSync.exceptions.ResourceNotFoundException;
import com.MASOWAC.readSync.models.*;
import com.MASOWAC.readSync.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ManageService {
    private final StaffRepository staffRepository;
    private final BookRepository bookRepository;
    private final ManageRepository manageRepository;

    public ManageService(StaffRepository staffRepository, BookRepository bookRepository,ManageRepository manageRepository) {
        this.staffRepository = staffRepository;
        this.bookRepository = bookRepository;
        this.manageRepository = manageRepository;
    }

    // Add a book with manage operation
  public Manage addBook(Long staffId, Long bookId){
        Optional<Staff>staffObj = staffRepository.findById(staffId);
        Optional<Book> bookObj = bookRepository.findById(bookId);

        if(staffObj.isPresent()&&bookObj.isPresent()) {
            Manage manage= new Manage(staffObj.get(), bookObj.get(),ManageOperation.ADD,LocalDateTime.now());
            return manageRepository.save(manage);
        }
        else{
            throw new RuntimeException("Staff or Book not found");
        }
    }
//    Update a book by staff
    public Manage updateBook(Long staffId, Long bookId){
        Optional<Staff>staffObj = staffRepository.findById(staffId);
        Optional<Book> bookObj = bookRepository.findById(bookId);

        if(staffObj.isPresent()&&staffObj.isPresent()){
            Staff staff = staffObj.get();
            Book book = bookObj.get();
            Manage manage= new Manage(staff, book,ManageOperation.UPDATE,LocalDateTime.now());
            return manageRepository.save(manage);
        }else{
            throw new RuntimeException("Staff or Book not found");
        }
    }
//Remove book
public Manage removeBook(Long staffId, Long bookId) {
    Optional<Staff> staffOpt = staffRepository.findById(staffId);
    Optional<Book> bookOpt = bookRepository.findById(bookId);

    if (staffOpt.isPresent() && bookOpt.isPresent()) {
        Staff staff = staffOpt.get();
        Book book = bookOpt.get();
        Manage manage = new Manage(staff, book, ManageOperation.REMOVE, LocalDateTime.now());
        return manageRepository.save(manage); // Track the REMOVE operation
    } else {
        throw new ResourceNotFoundException("Staff or Book not found!");
    }
}
//
}




