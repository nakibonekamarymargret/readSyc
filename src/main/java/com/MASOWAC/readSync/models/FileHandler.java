package com.MASOWAC.readSync.models;

import com.cloudinary.StoredFile;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "file_handler")
public class FileHandler {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    @Column(name="file_url")
    private String fileUrl;


    public FileHandler(){}

    public FileHandler( String fileName, String fileUrl){
        this.fileName=fileName;
        this.fileUrl=fileUrl;

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setName(String fileName) {
        this.fileName = fileName;
    }

}