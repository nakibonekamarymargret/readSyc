package com.MASOWAC.readSync.services;
import com.MASOWAC.readSync.models.FileHandler;
import com.MASOWAC.readSync.repository.FileHandlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class FileHandlerService {

    private final FileHandlerRepository fileHandlerRepository;
    private final  Cloudinary cloudinary;

    public FileHandlerService(FileHandlerRepository fileHandlerRepository,Cloudinary cloudinary){

        this.fileHandlerRepository=fileHandlerRepository;
        this.cloudinary=cloudinary;
    }
    public FileHandler addFile(String fileName, MultipartFile fileUrl) {
        try {
//            Extract orignal  file name and extension
            String originalFilename = fileUrl.getOriginalFilename();
            if (originalFilename == null) throw new IOException("File has no name!");

            String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + originalFilename);
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(fileUrl.getBytes());
            fos.close();
//            Upload File to Cloudinary
            String publicId = fileName.replaceAll("\\s+", "_"); // Replace spaces with underscores
            var pdf = cloudinary.uploader().upload(convFile, ObjectUtils.asMap(
                    "folder", "myFiles/",
                    "public_id", publicId,
                    "resource_type", "auto",//detects file type (image, video, or raw file)
                    "overwrite", true,
                    "use_filename", true,
                    "type", "upload"
            ));
            //File handler object
            var newFile = new FileHandler();
            newFile.setName(fileName);
            newFile.setFileUrl(pdf.get("url").toString()); // Save URL
            return this.fileHandlerRepository.save(newFile);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to upload the pdf file.");
        }
    }


}
