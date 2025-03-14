package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportsRepository extends JpaRepository<Reports, Long> {
    List<Reports> findAllByStaffId(Long staffId);
}
