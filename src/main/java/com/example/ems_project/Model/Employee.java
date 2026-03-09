package com.example.ems_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees_dto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name cannot be empty!")
    @Column(nullable = false) //database constraint
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty!")
    @Column(nullable = false)
    private String lastName;

    @Email(message = "Invalid Email Format!")
    @NotBlank(message = "Email ID cannot be empty")
    @Column(unique = true, nullable = false)
    private String emailId;

    @NotBlank(message = "Department cannot be empty!")
    private String department;
}
