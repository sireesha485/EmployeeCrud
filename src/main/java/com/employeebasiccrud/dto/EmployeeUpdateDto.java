package com.employeebasiccrud.dto;

public class EmployeeUpdateDto {
    private Integer EmployeeId;

    private String employeeName;

    private Long phone;

    private Integer aadhar;

    private String email;

    public Integer getEmployeeId() {
	return EmployeeId;
    }

    public void setEmployeeId(Integer employeeId) {
	EmployeeId = employeeId;
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
