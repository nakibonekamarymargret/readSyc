package com.MASOWAC.readSync.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import com.MASOWAC.readSync.models.Manage;
import com.MASOWAC.readSync.models.Reports;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name= "staff",
uniqueConstraints=@UniqueConstraint(columnNames={"loginId","email"})
)
public class Staff {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "staff_id") // Explicitly naming it to avoid confusion
    private Long staffId;
    @Column(nullable= false)
    private String loginId;

    @Column(nullable= false)
    private String email;

    @Column(nullable= false)
    private String firstName;

    @Column(nullable= false)
    private String lastName;

    @Column(nullable= false)
    @Size(min=8)
    private String password;

    @Column(nullable= false)
    @Size(max=17)
    private String contact;

//    One to many between staff and books
    @OneToMany(mappedBy="staff")
    private Set<Manage> managedBooks = new HashSet<>();
//One staff generates multiple reports
    @OneToMany(mappedBy ="staff")
    private Set<Reports>reports = new HashSet<>();

//    Many to many between staff and readers
    @ManyToMany
    @JoinTable(name= "staff-reader",
            joinColumns=@JoinColumn(name ="staff_id"),
            inverseJoinColumns= @JoinColumn(name ="reader_id")
    )
    private Set<Reader> readers = new HashSet<>();

    public Staff(){}

    public Staff( String loginId,String firstName,String lastName, String email, String password,String contact){
        this.loginId = loginId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contact = contact;
    }

    public Long getStaffId(){
        return staffId;
    }
    public void setStaffId(Long id){
        this.staffId =staffId;
    }

    public String getLoginId(){
        return loginId;
    }
    public void setId(String loginId){
        this.loginId =loginId;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName= lastName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email= email;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password= password;
    }

    public String getContact(){
        return contact;
    }
    public void setContact(String contact){
        this.contact= contact;
    }

   public Set<Manage>getManagedBooks(){
        return managedBooks;
   }
   public void setManagedBooks(Set<Manage>managedBooks){
        this.managedBooks= managedBooks;
   }

    public Set<Reader>getReader(){
        return readers;
    }
    public void setReaders(Set<Reader>readers){
        this.readers=readers;
    }

    public Set<Reports>getReports(){
        return reports;
    }
    public void setReports(Set<Reports>reports){
        this.reports=reports;
    }

    @Override
    public String toString(){
        return String.format(
                "Staff[staffId=%d,loginId='%s',firstName='%s',lastName='%s',email='%s',password='%s',contact='%s']",
                staffId,loginId, firstName,lastName,email,password,contact
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj==null || getClass()!=obj.getClass()) return false;
        Staff staff = (Staff)obj;
        return Objects.equals(loginId, staff.loginId)&& Objects.equals(email, staff.email);
    }
    @Override
    public int hashCode(){
        return Objects.hash(loginId,email);
    }
}
