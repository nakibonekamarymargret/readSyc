package com.MASOWAC.readSync.dto;

import com.MASOWAC.readSync.models.Book;
import com.MASOWAC.readSync.models.Reader;

import java.util.Set;

public class ReaderResponse{
 private Long id;
 private String firstName;
 private String lastName;
 private String email;
 private String address;
 private String phoneNumber;


 public ReaderResponse(Reader reader) {
  this.id = reader.getId();
  this.firstName = reader.getFirstName();
  this.lastName = reader.getLastName();
  this.email = reader.getEmail();
  this.address = reader.getAddress();
  this.phoneNumber = reader.getPhoneNumber();
 }
 public Long getId(){
  return id;
 }
 public void setId(Long id){
  this.id =id;
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



}
