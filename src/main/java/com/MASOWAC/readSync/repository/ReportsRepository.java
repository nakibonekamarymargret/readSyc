package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportsRepository extends JpaRepository<Reports, Long> {
    @Query("SELECT r FROM Reports r WHERE r.staff.staffId = :staffId")
    List<Reports> findAllByStaffId(@Param("staffId") Long staffId);
}
