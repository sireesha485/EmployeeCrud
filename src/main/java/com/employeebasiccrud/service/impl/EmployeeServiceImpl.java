package com.employeebasiccrud.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeebasiccrud.constants.Constants;
import com.employeebasiccrud.dto.EmployeeDto;
import com.employeebasiccrud.dto.EmployeeUpdateDto;
import com.employeebasiccrud.exception.EmployeeAlreadyExistsException;
import com.employeebasiccrud.exception.EmployeeNotFoundException;
import com.employeebasiccrud.model.Employee;
import com.employeebasiccrud.repository.EmployeeRepository;
import com.employeebasiccrud.service.EmployeeService;

/**
 * this class is for implementation of employee crud related functionality
 * 
 * @author sireesha
 * @version 1.0
 * @since 2020-07-31
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    /**
     * this method provides implementation for saving employee in data base
     * 
     * @param employeeDto user passes employee details
     * @return String
     * @throws EmployeeAlreadyExistsException if same employee existed
     */
    @Override
    public String saveEmployee(EmployeeDto employeeDto) throws EmployeeAlreadyExistsException {
	Employee employee = new Employee();
	Optional<Employee> oldEmployee = employeeRepository.findByAadharEquals(employeeDto.getAadhar());

	if (oldEmployee.isPresent()) {
	    logger.info("employee not found ");
	    throw new EmployeeAlreadyExistsException(Constants.EMPLOYEE_SAVE_FAIL);
	}
	BeanUtils.copyProperties(employeeDto, employee);
	employee = employeeRepository.save(employee);
	logger.info("employee saved");
	return String.format(Constants.EMPLOYEE_SAVE_SUCCESS, employee.getEmployeeId());
    }

    /**
     * this method provides implementation for updating employee in data base
     * 
     * @param employeeUpdateDto user passes employee details
     * @return String
     * @throws EmployeeNotFoundException if employee not existed
     */
    @Override
    public String updateEmployee(EmployeeUpdateDto employeeUpdateDto) throws EmployeeNotFoundException {
	Optional<Employee> oEmployee = employeeRepository.findById(employeeUpdateDto.getEmployeeId());

	if (!oEmployee.isPresent()) {
	    logger.info("employee not found ");
	    throw new EmployeeNotFoundException(
		    String.format(Constants.EMPLOYEE_NOT_FOUND, employeeUpdateDto.getEmployeeId()));
	}
	Employee employee = new Employee();
	BeanUtils.copyProperties(employeeUpdateDto, employee);

	employeeRepository.save(employee);

	return String.format(Constants.EMPLOYEE_UPDATE_SUCCESS, employeeUpdateDto.getEmployeeId());

    }

    /**
     * this method provides implementation for deleting employee in data base
     * 
     * @param id employee employee id
     * @return String
     * @throws EmployeeNotFoundException if employee not existed
     */
    @Override
    public String deleteEmployee(Integer id) throws EmployeeNotFoundException {
	Optional<Employee> oEmployee = employeeRepository.findById(id);

	if (oEmployee.isPresent()) {
	    employeeRepository.deleteById(id);
	} else {
	    logger.info("employee not found");
	    throw new EmployeeNotFoundException(String.format(Constants.EMPLOYEE_NOT_FOUND, id));
	}

	return String.format(Constants.EMPLOYEE_DELETE_SUCCESS, id);
    }

    /**
     * this method provides implementation for seraching employee in data base
     * 
     * @param employeeName employee name
     * @return List list of employees
     * @throws EmployeeNotFoundException if employee not existed
     */
    @Override
    public List<EmployeeUpdateDto> searchEmployee(String employeeName) throws EmployeeNotFoundException {
	List<Employee> employees = employeeRepository.findByEmployeeNameContaining(employeeName);
	if (employees.isEmpty()) {
	    logger.info("employee not found with given name");
	    throw new EmployeeNotFoundException("no employees found");
	}
	return employees.stream().map(EmployeeServiceImpl::getDtoFromEntity).collect(Collectors.toList());
    }

    /**
     * this method provides implementation for getting all employees
     * 
     * @return String
     * @throws EmployeeNotFoundException if employees not existed
     */
    @Override
    public List<EmployeeUpdateDto> getAll() throws EmployeeNotFoundException {

	List<Employee> employees = employeeRepository.findAll();
	if (employees.isEmpty())
	    throw new EmployeeNotFoundException("no employees found");
	return employees.stream().map(EmployeeServiceImpl::getDtoFromEntity).collect(Collectors.toList());
    }

    private static EmployeeUpdateDto getDtoFromEntity(Employee employee) {
	EmployeeUpdateDto employeeUpdateDto = new EmployeeUpdateDto();
	BeanUtils.copyProperties(employee, employeeUpdateDto);
	return employeeUpdateDto;
    }
}
