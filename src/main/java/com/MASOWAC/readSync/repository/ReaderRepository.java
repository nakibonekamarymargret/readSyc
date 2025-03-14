package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.Reader;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository  extends JpaRepository<Reader, Long>, JpaSpecificationExecutor<Reader> {
List<Reader> findAll(Specification<Reader> specification);

}
