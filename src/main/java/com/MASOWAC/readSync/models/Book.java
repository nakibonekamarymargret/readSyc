package com.MASOWAC.readSync.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_id_seq", allocationSize = 1)
    private Long id;
    @NotBlank(message ="Book title cant be blank")
    private String title;
    private String author;
    private String isbn;

    private int publishedYear;
    private boolean available;

//    Relationships
//    Many to many between books and readers ie a reader can borrow many books
//    and a book can be borrowed by many readers
    @ManyToMany(mappedBy="borrowedBooks")
    @JsonIgnore
    private Set<Reader> readers = new HashSet<>();

//    Many to one relationship between the book amd publisher
//    Many books are  published by one publisher
//     @JoinColumn when a book is created, it is assigned to its publisher

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "publisher_id", nullable = false)
//    @JsonBackReference
//    @JsonIgnore
    private Publisher publisher;

    public Book(){}
    public Book(String title,String author, String isbn, int publishedYear,boolean available ){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.available = available;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    public String getIsbn(){
        return isbn;
    }
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public int getPublishedYear(){
        return publishedYear;
    }
    public void setPublishedYear(int publishedYear){
        this.publishedYear = publishedYear;
    }

    public boolean getAvailable(){
        return available;
    }
    public void setAvailable(boolean available){
        this.available = available;
    }

    public Publisher getPublisher(){
        return publisher;
    }
    public void setPublisher(Publisher publisher){
        this.publisher =publisher;
    }

    public Set<Reader>getReaders(){
        return readers;
    }
    public void setReaders(Set<Reader>readers){
        this.readers=readers;
    }

    public String toString(){
        return String.format(
                "Book[id = %d, title ='%s', author ='%s', isbn ='%s', publishedYear =%d, available=%b,publisher='%s',readers='%s']",
                id,title,author,isbn,publishedYear,available,publisher.getPublisherName(), readers
                );
    }

}
