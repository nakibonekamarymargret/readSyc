package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.models.Reports;
import com.MASOWAC.readSync.models.Staff;
import com.MASOWAC.readSync.repository.ReportsRepository;
import com.MASOWAC.readSync.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportsService {
    private final ReportsRepository reportsRepository;
    private final StaffRepository staffRepository;

    public ReportsService (ReportsRepository reportsRepository,StaffRepository staffRepository){
        this.reportsRepository = reportsRepository;
        this.staffRepository = staffRepository;
    }
//    Creating a report
    public Reports createReport(Long staffId, String reportContent){
        Optional<Staff> staffObj = staffRepository.findByStaffId(staffId);

        if(staffObj.isPresent()){
            Staff staff = staffObj.get();
            Reports report = new Reports(staff,reportContent, LocalDateTime.now());
            return reportsRepository.save(report);

        }else{
            throw new RuntimeException("Staff not found");
        }
    }
//get report by id

    public Reports getReportById(Long reportId) {
        return reportsRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found!"));
    }
//    Retrieve all reports for a specific staff member
public List<Reports> getReportByStaffId(Long staffId) {
    return reportsRepository.findAllByStaffId(staffId);
}
    public Reports updateReport(Long reportId, String newContent) {
        Optional<Reports> reportOpt = reportsRepository.findById(reportId);

        if (reportOpt.isPresent()) {
            Reports report = reportOpt.get();
            report.setReportContent(newContent);
            report.setReportDate(LocalDateTime.now()); // Update timestamp
            return reportsRepository.save(report);
        } else {
            throw new RuntimeException("Report not found!");
        }
    }

    // Delete a report

    public void deleteReport(Long reportId) {
        if (reportsRepository.existsById(reportId)) {
            reportsRepository.deleteById(reportId);
        } else {
            throw new RuntimeException("Report not found!");
        }
    }
}
