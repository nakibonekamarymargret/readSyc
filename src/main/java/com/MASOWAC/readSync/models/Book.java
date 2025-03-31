package com.MASOWAC.readSync.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"id", "publisher"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_id_seq", allocationSize = 1)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;
    private boolean available;

    //    Relationships
//Many to one btween publisher and books, many books are published by one pub
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;
//Many to one btween staff and books, many books are maintained by one staff
    @ManyToOne
    @JoinColumn(name="staff_id")
    private Staff staff;

//    many to many between books and readers ie a book can be borrowed by many readers

    @ManyToMany(mappedBy = "borrowedBooks",fetch=FetchType.LAZY)
    @JsonIgnore
    private Set<Reader> borrowedBooks = new HashSet<>();
    public Book() {
    }
    public Long getId() {
        return id;
    }

    public Book(String title, String author, String isbn, int publishedYear, boolean available,Set<Reader> borrowedBooks) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.available = available;
        this.borrowedBooks = borrowedBooks;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Staff getPStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Set<Reader> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<Reader> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public String toString() {
        return String.format(
                "Book[id = %d, title ='%s', author ='%s', isbn ='%s', publishedYear =%d, available='%s',publisher='%s']",
                id, title, author, isbn, publishedYear, available, publisher.getPublisherName()
        );
    }

}
