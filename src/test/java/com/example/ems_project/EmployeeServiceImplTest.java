package com.example.ems_project;

import com.example.ems_project.DTO.EmployeeDTO;
import com.example.ems_project.Exception.EmployeeNotFoundException;
import com.example.ems_project.Model.Employee;
import com.example.ems_project.Repository.EmployeeRepository;
import com.example.ems_project.Service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//import static javax.management.Query.times;
//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        modelMapper=new ModelMapper();
        employeeService=new EmployeeServiceImpl(employeeRepository,modelMapper);

        employee=new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john@example.com");
        employee.setDepartment("IT");
    }

    @Test
    void testGetEmployeeById_Found(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeDTO result=employeeService.getEmployeeById(1L);
        assertNotNull(result);
        assertEquals("John",result.getFirstName());
    }

    @Test
    void testGetEmployeeById_NotFound(){
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->employeeService.getEmployeeById(2L));
    }

    @Test
    void testGetAllEmployeesPagedSorted(){
        List<Employee> employees= Arrays.asList(employee);
        Page<Employee> page=new PageImpl<>(employees);
        when(employeeRepository.findAll(PageRequest.of(0,5, Sort.by("id"))))
                .thenReturn(page);

        Page<EmployeeDTO> result=employeeService.getEmployeesPagedSorted(0,5,"id");
        assertEquals(1,result.getTotalElements());
        verify(employeeRepository,times(1)).findAll(PageRequest.of(0,5,Sort.by("id")));
    }

    @Test
    void testSaveEmployee(){

        // Prepare DTO with actual data
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("John");
        employeeDTO.setLastName("Doe");
        employeeDTO.setEmailId("john@example.com");
        employeeDTO.setDepartment("IT");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDTO result=employeeService.saveEmployee(employeeDTO);

        assertEquals(employee.getEmailId(), result.getEmailId());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }
}
