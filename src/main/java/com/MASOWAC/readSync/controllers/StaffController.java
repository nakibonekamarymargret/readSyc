package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.models.Staff;
import com.MASOWAC.readSync.services.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService){
        this.staffService =staffService;
    }
//    Create Staff
    @PostMapping("/create-staff")
    public ResponseEntity<Staff>createStaff(@RequestBody Staff staff){
        return ResponseEntity.ok(staffService.createStaff(staff));
    }
//    Retrieve all staff members
    @GetMapping
    public ResponseEntity<List<Staff>>getAllStaff(){
        return ResponseEntity.ok(staffService.getAllStaff());
    }
}
