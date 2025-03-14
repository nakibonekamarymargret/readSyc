package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff,Long> , JpaSpecificationExecutor<Staff> {
    Optional<Staff> findByStaffId(Long staffId);
}
