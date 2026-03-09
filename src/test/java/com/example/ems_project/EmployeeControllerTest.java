package com.example.ems_project;

import com.example.ems_project.Controller.EmployeeController;
import com.example.ems_project.DTO.EmployeeDTO;
import com.example.ems_project.Model.Employee;
import com.example.ems_project.Service.EmployeeService;
import com.example.ems_project.Service.EmployeeServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.boot.test.mock.mockito.MockBean;

import tools.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

//import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    private Employee employee;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        employee=new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john@example.com");
        employee.setDepartment("IT");
    }

    @Test
    void testGetEmployeeById() throws Exception{
//        when(employeeService.getEmployeeById(1L)).thenReturn(new EmployeeDTO());

        EmployeeDTO dto=new EmployeeDTO();
        dto.setId(1L);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmailId("john@example.com");
        dto.setDepartment("IT");

        when(employeeService.getEmployeeById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/employees/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testCreateEmployee() throws Exception{

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(1L);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmailId("john@example.com");
        dto.setDepartment("IT");

        when(employeeService.saveEmployee(any(EmployeeDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isCreated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.emailId").value("john@example.com"));
    }

}
