package com.employeebasiccrud.exception;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.employeebasiccrud.constants.Constants;

/**
 * this class is for handling exceptions across application
 * 
 * @author sireesha
 * @version 1.0
 * @since 2020-08-18
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {

	Map<String, Object> body = new LinkedHashMap<>();
	body.put("status", status.value());

	List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
		.collect(Collectors.toList());

	body.put("errors", errors);

	return new ResponseEntity<>(body, headers, status);

    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {

	HashMap<String, Object> body = new HashMap<>();
	body.put(Constants.MESSAGE, ex.getMessage());
	return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(Exception ex) {

	Map<String, Object> body = new LinkedHashMap<>();
	body.put("statusCode", 4044);
	body.put(Constants.MESSAGE, "employee Does Not Exist");

	return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmployeeAlreadyExistedException(Exception ex) {

	Map<String, Object> body = new LinkedHashMap<>();
	body.put("statusCode", 4031);
	body.put(Constants.MESSAGE, "employee already existed");

	return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
