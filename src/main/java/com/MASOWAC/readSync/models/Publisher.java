package com.MASOWAC.readSync.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="PUBLISHERS")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String publisherName;
    @NotNull(message = "Publication date can not be null")
    @Column(name = "publication_date", nullable = false)
    private Date publicationDate;
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
//one to many between the publsher and the book
//    A book is published by only one publisher

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Publisher(){}
     public Long getId(){
        return id;
     }
     public void setId(Long id){
        this.id=id;
     }

    public Publisher(String publisherName){
        this.publisherName= publisherName;
    }
    public String getPublisherName(){
        return publisherName;
    }

    public void setPublisherName(String publisherName){
        this.publisherName=publisherName;
    }

    public Timestamp getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt){
        this.createdAt= createdAt;
    }

    public Date getPublicationDate(){
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate){
        this.publicationDate=publicationDate;
    }
    public Timestamp getUpdatedAt(){
        return updatedAt;
    }
    public void setUpdatedAt(Timestamp updatedAt){
        this.updatedAt= updatedAt;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
    public String toString() {
        return String.format(
                "Publisher[id=%d, publisherName='%s',createdAt='%s', updatedAt='%s']",
                id, publisherName, createdAt, updatedAt
        );
    }

}
