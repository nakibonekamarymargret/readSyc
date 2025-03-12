package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>{
    Optional<Book> findByTitle(String title);
}
