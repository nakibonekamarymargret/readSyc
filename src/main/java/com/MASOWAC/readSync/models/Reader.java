package com.MASOWAC.readSync.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="READERS",
    uniqueConstraints={
        @UniqueConstraint(columnNames={"email","phone_number"})
    })
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message= "First name is required")
    @Column(name="first_name" )
    private String firstName;

    @NotBlank(message= "First name is required")
    @Column(nullable = false,name="last_name")
    private String lastName;

    @NotBlank(message= "Email is required and can not be empty")
    //the Pattern.Flag.CASE_INSENSITIVE,ignores the case sensitivity
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message="Enter a valid email address"
    )
    private String email;

    @NotBlank(message = "enter your address please")
    @Column(name="address")
    private String address;

    @NotBlank(message="Phone number is required")
    @Size(max=17)
    @Column(name="phone_number", unique = true)
    private String phoneNumber;

    //    Relationships
//    Many to many between books and readers ie a reader can borrow many books
//    and a book can be borrowed by many readers

    public Reader(){}
    public Reader( String firstName,String lastName, String email, String address,String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id =id;
    }
    public String getFirstName(){
        return firstName;
     }public void setFirstName(String firstName){
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
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address= address;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber= phoneNumber;
    }

    public String toString(){
        return String.format(
                "Reader[id=%d,firstName='%s',lastName='%s',email='%s',address='%s',phoneNumber='%s']",
                id, firstName,lastName,email,address,phoneNumber
        );
    }

}
