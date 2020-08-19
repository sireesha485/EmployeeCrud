package com.employeebasiccrud.service;

import java.util.List;

import com.employeebasiccrud.dto.EmployeeDto;
import com.employeebasiccrud.dto.EmployeeUpdateDto;
import com.employeebasiccrud.exception.EmployeeAlreadyExistsException;
import com.employeebasiccrud.exception.EmployeeNotFoundException;

public interface EmployeeService {
    public String saveEmployee(EmployeeDto employeeDto) throws EmployeeAlreadyExistsException;

    public String updateEmployee(EmployeeUpdateDto employeeDto) throws EmployeeNotFoundException;

    public String deleteEmployee(Integer id) throws EmployeeNotFoundException;

    public List<EmployeeUpdateDto> searchEmployee(String employeeName) throws EmployeeNotFoundException;

    public List<EmployeeUpdateDto> getAll() throws EmployeeNotFoundException;
}
