package com.employeebasiccrud.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeeDto {
    @NotBlank
    private String employeeName;

    private Long phone;
    @NotNull
    private Integer aadhar;
    @NotBlank
    @Pattern(regexp = "^(.+)@(.+)$", message = "enter valid email")
    private String email;

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
