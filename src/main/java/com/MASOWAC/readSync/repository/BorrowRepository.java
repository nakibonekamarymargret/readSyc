package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow,Long> {
    List<Borrow> findByReaderId(Long readerId); // Get all books borrowed by a reader
    List<Borrow> findByBookId(Long bookId);//Get all readers who borrowed a book
}
