package com.MASOWAC.readSync.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
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
//    Many to many between books and readers ie a reader can borrow many books
//    and a book can be borrowed by many readers
    @ManyToMany(mappedBy="borrowedBooks")
    private Set<Reader> readers = new HashSet<>();

//    Many to one relationship between the book amd publisher
//    Many books can be published by one publisher
//    Foriegn key to publisher
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
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

    public Set<Reader>getReader(){
        return readers;
    }
    public void setReaders(Set<Reader>readers){
        this.readers=readers;
    }
    public String toString(){
        return String.format(
                "Book[id = %d, title ='%s', author ='%s', isbn ='%s', publishedYear =%d, available=%b,publisher='%s']",
                id,title,author,isbn,publishedYear,available,publisher.getPublisherName()
                );
    }

}
