package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.models.UploadedFile;
import com.MASOWAC.readSync.repository.UploadedFileRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class UploadedFileService {

    private final UploadedFileRepository uploadedFileRepository;
    private final Cloudinary cloudinary;
    public UploadedFileService(UploadedFileRepository uploadedFileRepository, Cloudinary cloudinary){
        this.uploadedFileRepository=uploadedFileRepository;
        this.cloudinary=cloudinary;

    }

    public UploadedFile uploadFile(String fileName, MultipartFile fileUrl ){

        try{
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileUrl.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(fileUrl.getBytes());
            fos.close();
            var pic = cloudinary.uploader().upload(convFile, ObjectUtils.asMap("folder", "/readSync/"));

            var newFile = new UploadedFile();
            newFile.setFileName(fileName);
            newFile.setFileUrl(pic.get("url").toString());

            return this.uploadedFileRepository.save(newFile);
        }catch(IOException e){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to upload the file.");

        }

    }

    public Optional<UploadedFile> getFile(Long id) {
        return uploadedFileRepository.findById(id);
    }
}
