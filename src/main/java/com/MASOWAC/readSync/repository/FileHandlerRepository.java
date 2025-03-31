package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.FileHandler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileHandlerRepository  extends JpaRepository<FileHandler, Long> {
}
