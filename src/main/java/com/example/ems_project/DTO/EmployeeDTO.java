package com.example.ems_project.DTO;

import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private  String lastName;
    private String emailId;
    private String department;
}
