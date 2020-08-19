package com.employeebasiccrud.dto;

import java.util.List;

public class EmployeeResponseDto {

    private List<EmployeeUpdateDto> employeeUpdateDto;

    private Integer statusCode;

    private String statusMessage;

    public List<EmployeeUpdateDto> getEmployeeUpdateDto() {
	return employeeUpdateDto;
    }

    public void setEmployeeUpdateDto(List<EmployeeUpdateDto> employeeUpdateDto) {
	this.employeeUpdateDto = employeeUpdateDto;
    }

    public Integer getStatusCode() {
	return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
	this.statusCode = statusCode;
    }

    public String getStatusMessage() {
	return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
	this.statusMessage = statusMessage;
    }
}
