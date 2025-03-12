package com.MASOWAC.readSync.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

//THis is a DTO that is used to modify my http response messages
 public class ReaderResponse{
 private Long id;
 private String firstName;
 private String lastName;
 private String email;
 private String address;
 private String phoneNumber;

 public ReaderResponse(Long id, String firstName,String lastName, String email, String address,String phoneNumber){
  this.id =id;
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
  this.address= phoneNumber;
 }

}
