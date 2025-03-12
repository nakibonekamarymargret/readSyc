package com.MASOWAC.readSync.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    public void setAvailable(boolean author){
        this.available = available;
    }

    public String toString(){
        return String.format(
                "Book[id = %d, title ='%s', author ='%s', isbn ='%s', publishedYear =%d, available=%b]",
                id,title,author,isbn,publishedYear,available
                );
    }

}
