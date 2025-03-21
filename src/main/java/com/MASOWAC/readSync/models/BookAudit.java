package com.MASOWAC.readSync.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class BookAudit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "operation")
    private String operation;

    @Column(name = "old_title")
    private String oldTitle;

    @Column(name = "new_title")
    private String newTitle;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public Long getBookId(){
        return bookId;
    }

    public void setBookId(Long bookId){
        this.bookId = bookId;
    }

    public String getOperation(){
        return operation;
    }

    public void setOperation(String operation){
        this.operation = operation;
    }

    public String getOldTitle(){
        return oldTitle;
    }

    public void setOldTitle(String old_title){
        this.oldTitle = oldTitle;
    }

    public String getNewTitle(){
        return newTitle;
    }

    public void setNewTitle(String newTitle){
        this.newTitle = newTitle;
    }

    public LocalDateTime getLocalDateTime(){
        return modifiedAt;
    }

    public void setLocalDateTime(LocalDateTime modifiedAt){
        this.modifiedAt = modifiedAt;
    }

    public String toString(){
        return String.format(
                "BookAudit[id =%d,operation='%s',oldTitle='%s',newTitle ='%s' ] ",
                id,operation,oldTitle,newTitle
        );
    }
}
