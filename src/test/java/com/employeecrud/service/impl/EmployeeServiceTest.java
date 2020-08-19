package com.employeecrud.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.employeebasiccrud.dto.EmployeeDto;
import com.employeebasiccrud.dto.EmployeeUpdateDto;
import com.employeebasiccrud.exception.EmployeeAlreadyExistsException;
import com.employeebasiccrud.exception.EmployeeNotFoundException;
import com.employeebasiccrud.model.Employee;
import com.employeebasiccrud.repository.EmployeeRepository;
import com.employeebasiccrud.service.impl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;
    Employee employee = new Employee();
    Employee employee2 = new Employee();
    List<Employee> employees = new ArrayList();

    @Before
    public void setup() {
	employee.setEmployeeId(1);
	employee.setEmployeeName("testing");
	employee.setPhone(5666L);
	employee.setAadhar(8676);
	employee2.setEmployeeId(2);
	employee2.setEmployeeName("test");
	employee2.setAadhar(86769);
	employees.add(employee);

	employees.add(employee2);
	when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
	when(employeeRepository.findAll()).thenReturn(employees);
	when(employeeRepository.findByEmployeeNameContaining("testing")).thenReturn(employees);
    }

    @Test
    public void TestSave() throws EmployeeAlreadyExistsException {
	EmployeeDto dto = new EmployeeDto();
	dto.setEmployeeName("testing");

	when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
	assertTrue(employeeService.saveEmployee(dto).contains("1"));
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void TestUpdateFail() throws EmployeeNotFoundException {
	EmployeeUpdateDto dto = new EmployeeUpdateDto();
	dto.setEmployeeName("testing");
	dto.setEmployeeId(2);

	employeeService.updateEmployee(dto);
    }

    @Test
    public void TestUpdate() throws EmployeeNotFoundException {
	EmployeeUpdateDto dto = new EmployeeUpdateDto();
	dto.setEmployeeName("testing");
	dto.setEmployeeId(1);

	assertTrue(employeeService.updateEmployee(dto).contains("update"));
    }

    @Test
    public void TestSearch() throws EmployeeNotFoundException {

	assertEquals(2, employeeService.searchEmployee("testing").size());
    }

    @Test
    public void TestgetAll() throws EmployeeNotFoundException {

	assertEquals(2, employeeService.getAll().size());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void TestDeleteFail() throws EmployeeNotFoundException {

	employeeService.deleteEmployee(2);
    }

    public void TestDelete() throws EmployeeNotFoundException {

	assertTrue(employeeService.deleteEmployee(1).contains("successfully"));
    }
}
