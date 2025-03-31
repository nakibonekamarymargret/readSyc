package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff,Long> , JpaSpecificationExecutor<Staff> {
    Optional<Staff> findByStaffId(Long staffId);


    @Query("SELECT s FROM Staff s WHERE CONCAT(s.firstName, ' ', s.lastName) = :fullName")
    Optional<Staff> findByFullName(@Param("fullName") String fullName);}
