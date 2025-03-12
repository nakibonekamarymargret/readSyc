package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository  extends JpaRepository<Publisher, Long> {
}
