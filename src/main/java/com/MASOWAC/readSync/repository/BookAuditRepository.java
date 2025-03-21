package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.BookAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuditRepository extends JpaRepository<BookAudit,Long> {
}
