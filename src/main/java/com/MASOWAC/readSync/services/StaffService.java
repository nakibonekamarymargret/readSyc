package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.models.Staff;
import com.MASOWAC.readSync.repository.StaffRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository){
        this.staffRepository =staffRepository;
    }
//    Applying CRUD
//    Creating a Staff
    public Staff createStaff(Staff staff){
        return staffRepository.save(staff);
    }

//    Returning all staff members
    public List<Staff>getAllStaff(){
        return staffRepository.findAll();
    }

//    Returning staff by id
    public Optional<Staff>getStaffById(Long id){
        return staffRepository.findById(id);
    }

//    Updating the staff
    public Staff updateStaff(Long id, Staff staffDetails) {
        return staffRepository.findById(id).map(staff -> {
            // Copy properties from staffDetails to staff (ignores null values and doesn't copy the id)
            BeanUtils.copyProperties(staffDetails, staff, "id");
            return staffRepository.save(staff);
        }).orElseThrow(() -> new RuntimeException("Staff not found with id " + id));
    }

    public void deleteStaff(Long id){
        staffRepository.deleteById(id);
    }
}
