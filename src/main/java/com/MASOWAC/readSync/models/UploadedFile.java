package com.MASOWAC.readSync.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="file-table")
public class UploadedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileUrl;

    @Column(name = "upload_time")
    private LocalDateTime uploadTime = LocalDateTime.now();

    public UploadedFile(){}

    public UploadedFile(String fileName,String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id =id;
    }
    public String getFileName(){
        return fileName;
    }
    public void setFileName(String  fileName){
        this.fileName =fileName;
    }
    public String getFileUrl(){
        return fileUrl;
    }
    public void setFileUrl(String  fileUrl){
        this.fileUrl =fileUrl;
    }

    public  LocalDateTime getUploadedTime(){
        return uploadTime;
    }
    public void setUploadedTime(LocalDateTime uploadTime){
        this.uploadTime =uploadTime;
    }

    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return String.format(
                "UploadedFile[id=%d, fileName='%s',fileUrl='%s',uploadedTime= '%s']",
                id,fileName,fileUrl,uploadTime.format(formatter)
        );
    }
}
