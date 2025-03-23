package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.models.UploadedFile;
import com.MASOWAC.readSync.repository.UploadedFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadedFileService {

    private final UploadedFileRepository uploadedFileRepository;
    public UploadedFileService(UploadedFileRepository uploadedFileRepository){
        this.uploadedFileRepository=uploadedFileRepository;
    }
    public void saveFile(MultipartFile file) throws IOException {
        UploadedFile myFile =new UploadedFile();
        myFile.setFile(file.getBytes());
        uploadedFileRepository.save(myFile);

    }
//    Download file by id
    public UploadedFile getFile(Long id){
      return   uploadedFileRepository.findById(id).get();
    }
}
