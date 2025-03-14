package com.MASOWAC.readSync.repository;

import com.MASOWAC.readSync.models.Manage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManageRepository extends JpaRepository<Manage,Long> {
    List<Manage> findAllByStaffId(Long staffId);
}
