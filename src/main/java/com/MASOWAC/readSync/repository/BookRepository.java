package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.dto.BookDTO;
import com.MASOWAC.readSync.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    List<Book> findByPublisherId(Long publisherId);

    //    Pagination
    Page<Book> findAll(Pageable pageable);

    List<Book> findBookByAuthor(String author, Pageable pageable);

    Page<Book> findBookByAvailable(boolean available, Pageable pageable);

    List<Book> findBookByPublishedYear(int publishedYear, Pageable pageable);

    //    Returning book without publisher
    @Query("SELECT new com.MASOWAC.readSync.dto.BookDTO(b.id, b.title, b.author, b.isbn, b.publishedYear, b.available) FROM Book b")
    Page<BookDTO> findAllBooksWithoutPublisher(Pageable pageable);

    //    Filtering books
    @Query("SELECT new com.MASOWAC.readSync.dto.BookDTO(b.id, b.title, b.author, b.isbn, b.publishedYear, b.available) FROM Book b WHERE (:title IS NULL OR b.title LIKE %:title%) AND (:author IS NULL OR b.author LIKE %:author%) AND (:publishedYear IS NULL OR b.publishedYear = :publishedYear) AND (:available IS NULL OR b.available = :available)")
    List<BookDTO> findBooksByFilters(
            @Param("title") String title,
            @Param("author") String author,
            @Param("publishedYear") Integer publishedYear,
            @Param("available") Boolean available);
}
