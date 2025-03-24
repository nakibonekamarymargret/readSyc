package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.models.UploadedFile;
import com.MASOWAC.readSync.services.UploadedFileService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/file")
public class UploadedFileController {

    private final UploadedFileService uploadedFileService;

    public UploadedFileController(UploadedFileService uploadedFileService){
        this.uploadedFileService= uploadedFileService;
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("fileName") String fileName,
            @RequestPart("fileUrl") MultipartFile fileUrl) {

        if (fileUrl.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty!");
        }

        String message = String.format("File '%s' uploaded successfully!", fileName);
        return ResponseEntity.ok(message);
    }

//    @GetMapping("/download/{id}")
//    public ResponseEntity<UploadedFile> downloadFile(@PathVariable("id")Long id){
//
//        UploadedFile file =uploadedFileService.getFile(id);
//        return ResponseEntity.ok().header("Content-type","file/pdf").body(file.getFile());
//    }
}
