package com.example.ems_project.Exception;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String message){
        super(message);
    }
}

//This exception will be thrown when an employee ID is not found.