package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    String FILTER_BOOK_ON_AUTHOR_AND_TITLE_QUERY = "SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')) AND LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))";

    Optional<Book> findByTitle(String title);

    List<Book> findByPublisherId(Long publisherId);

    Page<Book> findByPublishedYearAndTitleContainingAndAuthorContaining(int publishedYear, String title, String author, Pageable pageable);

    //    Filtering by author and title
    @Query(FILTER_BOOK_ON_AUTHOR_AND_TITLE_QUERY)
    Page<Book> findByAuthorAndTitle(@Param("author") String author, @Param("title") String title, Pageable pageable);

}
