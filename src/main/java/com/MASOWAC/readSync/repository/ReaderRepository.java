package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.dto.ReaderResponse;
import com.MASOWAC.readSync.models.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository  extends JpaRepository<Reader, Long>, JpaSpecificationExecutor<Reader> {
    List<Reader> findAll(Specification<Reader> specification);

    //Using pagination to return readers
    Page<Reader> findAll(Pageable pageable);
}