package com.employeebasiccrud.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.employeebasiccrud.dto.EmployeeDto;
import com.employeebasiccrud.dto.EmployeeUpdateDto;
import com.employeebasiccrud.exception.EmployeeAlreadyExistsException;
import com.employeebasiccrud.exception.EmployeeNotFoundException;
import com.employeebasiccrud.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
    @InjectMocks
    EmployeeController EmployeeController;

    @Mock
    EmployeeService employeeService;

    EmployeeDto dto = new EmployeeDto();

    EmployeeUpdateDto updateDto = new EmployeeUpdateDto();

    @Before
    public void setup() {
	dto.setEmployeeName("testing");
	dto.setAadhar(4345);
	dto.setPhone(338888L);
	dto.setEmail("testing@gmail.com");
    }

    @Test
    public void TestSave() throws EmployeeAlreadyExistsException {
	EmployeeDto dto = new EmployeeDto();

	assertEquals(201, EmployeeController.saveEmployee(dto).getStatusCodeValue());
    }

    @Test
    public void TestUpdate() throws EmployeeNotFoundException {

	updateDto.setEmployeeName("testing");
	updateDto.setEmployeeId(1);
	updateDto.setAadhar(6668);
	updateDto.setPhone(9976L);
	assertEquals(201, EmployeeController.updateEmployee(updateDto).getStatusCodeValue());
    }

    @Test
    public void TestSearch() throws EmployeeNotFoundException {

	assertEquals("employee details retrived successfully",
		EmployeeController.searchEmployee("testing").getStatusMessage());
    }

    @Test
    public void TestgetAll() throws EmployeeNotFoundException {

	assertEquals("employee details retrived successfully", EmployeeController.getEmployees().getStatusMessage());
    }

    @Test
    public void TestDelete() throws EmployeeNotFoundException {

	assertEquals(403, EmployeeController.deleteEmployee(1).getStatusCodeValue());
    }
}
