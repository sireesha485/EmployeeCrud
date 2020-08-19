package com.employeebasiccrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeebasiccrud.dto.EmployeeDto;
import com.employeebasiccrud.dto.EmployeeResponseDto;
import com.employeebasiccrud.dto.EmployeeUpdateDto;
import com.employeebasiccrud.exception.EmployeeAlreadyExistsException;
import com.employeebasiccrud.exception.EmployeeNotFoundException;
import com.employeebasiccrud.service.EmployeeService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * this class is for employee crud related functionality
 * 
 * @author sireesha
 * @version 1.0
 * @since 2020-07-31
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    /**
     * this method allows employee for saving employee in data base
     * 
     * @param employeeDto employee passes employee details
     * @return ResponseEntity
     * @throws EmployeeAlreadyExistsException if same employee existed
     */
    @ApiResponses(value = { @ApiResponse(code = 4031, message = "employee already existed"),
	    @ApiResponse(code = 201, message = "employee saved  successfully") })
    @PostMapping("/newEmployee")
    public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto)
	    throws EmployeeAlreadyExistsException {
	return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    /**
     * this method allows employee for updating employee in data base
     * 
     * @param employeeDto user passes employee details
     * @return ResponseEntity
     * @throws EmployeeNotFoundException if employee not existed
     */
    @ApiResponses(value = { @ApiResponse(code = 4044, message = "employee not  found"),
	    @ApiResponse(code = 200, message = "employee updated  successfully") })
    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeUpdateDto employeeDto)
	    throws EmployeeNotFoundException {
	return new ResponseEntity<>(employeeService.updateEmployee(employeeDto), HttpStatus.OK);
    }

    /**
     * this method allows employee for seraching employee in data base
     * 
     * @param name employee name
     * @return List list of employees
     * @throws EmployeeNotFoundException if employee not existed
     */
    @ApiResponses(value = { @ApiResponse(code = 4044, message = "employee not  found"),
	    @ApiResponse(code = 4032, message = "employee serach results retrived successfully") })
    @GetMapping("{name}")
    public EmployeeResponseDto searchEmployee(@RequestParam String name) throws EmployeeNotFoundException {
	EmployeeResponseDto responseDto = new EmployeeResponseDto();
	List<EmployeeUpdateDto> dtos = employeeService.searchEmployee(name);
	responseDto.setEmployeeUpdateDto(dtos);
	responseDto.setStatusCode(4032);
	responseDto.setStatusMessage("employee details retrived successfully");
	logger.info("employee search success");
	return responseDto;
    }

    /**
     * this method allows employee for deleting employee in data base
     * 
     * @param id employee employee id
     * @return ResponseEntity
     * @throws EmployeeNotFoundException if employee not existed
     */
    @ApiResponses(value = { @ApiResponse(code = 4044, message = "employee not  found"),
	    @ApiResponse(code = 403, message = "employee deleted  successfully") })
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) throws EmployeeNotFoundException {
	return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.FORBIDDEN);

    }

    /**
     * this method allows employee for getting all employees
     * 
     * @return EmployeeResponseDto
     * @throws EmployeeNotFoundException if employees not existed
     */
    @ApiResponses(value = { @ApiResponse(code = 4044, message = "employee not  found"),
	    @ApiResponse(code = 4000, message = "employees retrived  successfully") })
    @GetMapping("/allEmployees")
    public EmployeeResponseDto getEmployees() throws EmployeeNotFoundException {
	EmployeeResponseDto responseDto = new EmployeeResponseDto();
	List<EmployeeUpdateDto> dtos = employeeService.getAll();
	responseDto.setEmployeeUpdateDto(dtos);
	responseDto.setStatusCode(4000);
	responseDto.setStatusMessage("employee details retrived successfully");
	logger.info("employee search success");
	return responseDto;
    }

}
