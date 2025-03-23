package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedFileRepository extends JpaRepository<UploadedFile,Long> {
}
