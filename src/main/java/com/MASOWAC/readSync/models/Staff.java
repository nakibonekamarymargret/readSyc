package com.MASOWAC.readSync.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "staff", uniqueConstraints = @UniqueConstraint(columnNames = {"loginId", "email"}))
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Long staffId;

    @Column(nullable = false)
    @NotNull(message = "Login ID cannot be null")
    private String loginId;

    @Column(nullable = false)
    @NotEmpty(message = "Email cannot be null")
    private String email;

    @Column(name = "first_name", nullable = false)
    @NotNull(message = "First name cannot be null")
    private String firstName;

    private String lastName;

    @Column(nullable = false)
    @Size(min = 8, message = "Password must be at least 8 characters")
    @NotBlank(message = "Password required !!!!")
    private String password;

    @Column(nullable = false)
    @Size(max = 17, message = "Contact number must be a valid length")
    @NotNull(message = "Contact cannot be null")
    private String contact;

    // One-to-many relationship between staff and reports
    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private Set<Reports> reports = new HashSet<>();

//    Between  a staff and books
    @OneToMany(mappedBy="staff")
    @JsonIgnore//prevents an additional table
    private Set<Book>books =new HashSet<>();
    // Many-to-many relationship between staff and readers
    @ManyToMany
    @JoinTable(
            name = "staff_readers",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "reader_id")
    )
    @JsonIgnore
    private Set<Reader> readers = new HashSet<>();

    // Default constructor
    public Staff() {
    }

    // Constructor to initialize fields
    public Staff(String firstName, String lastName, String contact, String email, String loginId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
    }

    // Getter and setter methods
    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<Reader> getReaders() {
        return readers;
    }

    public void setReaders(Set<Reader> readers) {
        if (readers != null) {
            this.readers = readers;
        } else {
            this.readers = new HashSet<>();
        }
    }

    public Set<Reports> getReports() {
        return reports;
    }

    public void setReports(Set<Reports> reports) {
        if (reports != null) {
            this.reports = reports;
        } else {
            this.reports = new HashSet<>();
        }
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        if (books != null) {
            this.books = books;
        } else {
            this.books = new HashSet<>();
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Staff[staffId=%d, loginId='%s', firstName='%s', lastName='%s', email='%s', contact='%s']",
                staffId, loginId, firstName, lastName, email, contact);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Staff staff = (Staff) obj;
        return Objects.equals(loginId, staff.loginId) && Objects.equals(email, staff.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginId, email);
    }


}
