package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.models.UploadedFile;
import com.MASOWAC.readSync.services.UploadedFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadedFileController {

    private final UploadedFileService uploadedFileService;

    public UploadedFileController(UploadedFileService uploadedFileService){
        this.uploadedFileService= uploadedFileService;
    }
    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        uploadedFileService.saveFile(file);
        return new ResponseEntity("Uploaded successfully", HttpStatus.CREATED);
    }

    @PostMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("file")Long id){

        UploadedFile file =uploadedFileService.getFile(id);
        return ResponseEntity.ok().header("Content-type","file/jpg").body(file.getFile());
    }
}
