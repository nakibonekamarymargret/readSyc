package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.models.Book;
import com.MASOWAC.readSync.models.Staff;
import com.MASOWAC.readSync.services.BookService;
import com.MASOWAC.readSync.services.StaffService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;
    private final BookService bookService;

    public StaffController(StaffService staffService,BookService bookService){
        this.staffService =staffService;
        this.bookService =bookService;
    }
//    Create Staff
    @PostMapping("/create-staff")
    public ResponseEntity<Staff>createStaff(@RequestBody Staff staff){
        return ResponseEntity.ok(staffService.createStaff(staff));
    }
//    Retrieve all staff members
    @GetMapping
    public ResponseEntity<Page<Staff>>getAllStaff(
            @RequestParam(defaultValue ="0")int page,//offset
            @RequestParam(defaultValue ="5")int size,//limit
            @RequestParam(defaultValue ="staffId")String sortBy,//sorting
            @RequestParam(defaultValue ="asc") String order//order
    ){
        Sort sort = "asc".equalsIgnoreCase(order) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return ResponseEntity.ok(staffService.getAllStaff(pageable));
    }
    // Assign a book to a staff member using book title and staff name
    @PostMapping("/{bookTitle}/assign/{staffName}")
    public ResponseEntity<Map<String, Object>> assignBookToStaff(@PathVariable String bookTitle, @PathVariable String staffName) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Call the method in StaffService
            Staff updatedStaff = staffService.manageBooks(bookTitle, staffName);

            response.put("status", "success");
            response.put("message", "Book '" + bookTitle + "' assigned to staff member '" + staffName + "' successfully");
            response.put("data", updatedStaff);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", "An error occurred while assigning the book to staff");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
