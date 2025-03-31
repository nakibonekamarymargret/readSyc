package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.models.FileHandler;
import com.MASOWAC.readSync.services.FileHandlerService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class FileHandlerController {
    private final FileHandlerService fileHandlerService;
    public FileHandlerController(FileHandlerService fileHandlerService){
        this. fileHandlerService = fileHandlerService;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<FileHandler> addFile(@RequestParam("fileName") String fileName,
                                               @RequestParam("fileUrl") MultipartFile fileUrl) {
        var res = this.fileHandlerService.addFile(fileName, fileUrl);

        return ResponseEntity.ok(res);
    }


}