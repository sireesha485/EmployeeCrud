package com.employeebasiccrud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeId;

    private String employeeName;

    private Long phone;

    private Integer aadhar;

    private String email;

    public Integer getEmployeeId() {
	return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
	this.employeeId = employeeId;
    }

    public String getEmployeeName() {
	return employeeName;
    }

    public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
    }

    public Long getPhone() {
	return phone;
    }

    public void setPhone(Long phone) {
	this.phone = phone;
    }

    public Integer getAadhar() {
	return aadhar;
    }

    public void setAadhar(Integer aadhar) {
	this.aadhar = aadhar;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }
}
