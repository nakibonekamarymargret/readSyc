package com.MASOWAC.readSync.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="borrows")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="reader_id", nullable = false)
    private Reader reader;

    @ManyToOne
    @JoinColumn(name="book_id", nullable = false)
    private Book book;

    @Column(name="borrow_date", nullable = false)
    private LocalDateTime borrowDate;

    @Column(name="return_date")
    private LocalDateTime returnDate; // Nullable

    // Getters & Setters
    public Borrow(){}
    public Borrow( Reader reader,Book book, LocalDateTime borrowDate, LocalDateTime returnDate){
        this.reader = reader;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id =id;
    }
    public Reader getReader(){
        return reader;
    }
    public void setReader(Reader reader){
        this.reader =reader;
    }
    public Book getBook(){
        return book;
    }
    public void setBook(Book book){
        this.book =book;
    }

    public LocalDateTime  getBorrowDate(){
        return borrowDate;
    }
    public void setBorrowDate(LocalDateTime borrowDate){
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDate(){
        return returnDate;
    }
    public void setReturnDate(LocalDateTime returnDate){
        this.returnDate = returnDate;
    }

    @Override
    public String toString(){
        return String.format("Borrow{id =%d," +
                "reader='%s'," + "book ='%s'," +
                "borrowDate= %tF," +
                "returnDate= %tF}",
                id,reader,book,borrowDate,returnDate
        ) ;
    }

}
