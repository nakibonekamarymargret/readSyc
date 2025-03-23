package com.MASOWAC.readSync.models;

import jakarta.persistence.*;

@Entity
@Table(name="file_table")
public class UploadedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition ="BLOB")
    private byte[] file;

    public UploadedFile(){}

    public UploadedFile(byte[] file) {
        this.file = file;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id =id;
    }
    public byte[] getFile(){
        return file;
    }
    public void setFile(byte[] file){
        this.file =file;
    }
}
