package com.MASOWAC.readSync.models;

import com.MASOWAC.readSync.enums.ManageOperation;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Manage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managementId; // Primary Key

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff; // Foreign Key to Staff

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book; // Foreign Key to Book

    @Enumerated(EnumType.STRING)
    private ManageOperation operationType; // Enum for ADD, UPDATE, REMOVE

    private LocalDateTime managementDate;

    // Default Constructor
    public Manage() {}

    // Parameterized Constructor
    public Manage(Staff staff, Book book, ManageOperation operationType, LocalDateTime managementDate) {
        this.staff = staff;
        this.book = book;
        this.operationType = operationType;
        this.managementDate = managementDate;
    }

    // Getters and Setters
    public Long getManagementId() {
        return managementId;
    }

    public void setManagementId(Long managementId) {
        this.managementId = managementId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ManageOperation getOperationType() {
        return operationType;
    }

    public void setOperationType(ManageOperation operationType) {
        this.operationType = operationType;
    }

    public LocalDateTime getManagementDate() {
        return managementDate;
    }

    public void setManagementDate(LocalDateTime managementDate) {
        this.managementDate = managementDate;
    }

    // toString Method for Debugging
    @Override
    public String toString() {
        return "Manage{" +
                "managementId=" + managementId +
                ", staff=" + (staff != null ? staff.getId() : "null") +
                ", book=" + (book != null ? book.getId() : "null") +
                ", operationType=" + operationType +
                ", managementDate=" + managementDate +
                '}';
    }

    // Equals Method to Compare Objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Manage manage = (Manage) obj;
        return Objects.equals(managementId, manage.managementId) &&
                Objects.equals(staff, manage.staff) &&
                Objects.equals(book, manage.book) &&
                operationType == manage.operationType &&
                Objects.equals(managementDate, manage.managementDate);
    }

    // HashCode Method for Object Hashing
    @Override
    public int hashCode() {
        return Objects.hash(managementId, staff, book, operationType, managementDate);
    }
}
