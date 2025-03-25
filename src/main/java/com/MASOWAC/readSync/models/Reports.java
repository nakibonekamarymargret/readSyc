package com.MASOWAC.readSync.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Takes staff_id as the FK
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    private String reportContent;
    private LocalDateTime reportDate;

    public Reports() {}

    public Reports(Staff staff, String reportContent, LocalDateTime reportDate) {
        this.staff = staff;
        this.reportContent = reportContent;
        this.reportDate = reportDate;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public Staff getStaff(){
        return staff;
    }
    public void setStaff(Staff staff){
        this.staff = staff;
    }

    public String getReportContent(){
        return reportContent;
    }
    public void setReportContent(String reportContent){
        this.reportContent = reportContent;
    }

    public LocalDateTime getReportDate(){
        return reportDate;
    }
    public void setReportDate(LocalDateTime reportDate){
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return "Reports{" +
                "id=" + id +
                ", staff=" + (staff != null ? staff.getStaffId() : "null") + // Avoids potential null reference
                ", reportContent='" + reportContent + '\'' +
                ", reportDate=" + reportDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reports reports = (Reports) obj;
        return Objects.equals(id, reports.id) &&
                Objects.equals(staff, reports.staff) &&
                Objects.equals(reportContent, reports.reportContent) &&
                Objects.equals(reportDate, reports.reportDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, staff, reportContent, reportDate);
    }
}
