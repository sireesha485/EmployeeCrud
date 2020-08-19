package com.employeebasiccrud.constants;

public class Constants {
    private Constants() {
//added to prevent object creation
    }

    public static final String MESSAGE = "message";
    public static final String EMPLOYEE_NOT_FOUND = "employee with id %s not found";
    public static final String EMPLOYEE_SAVE_SUCCESS = "employee with id %s saved successfully";
    public static final String EMPLOYEE_UPDATE_SUCCESS = "employee with id %s updated successfully";
    public static final String EMPLOYEE_DELETE_SUCCESS = "employee with id %s deleted successfully";
    public static final String EMPLOYEE_SAVE_FAIL = "employee with already existed";
}
