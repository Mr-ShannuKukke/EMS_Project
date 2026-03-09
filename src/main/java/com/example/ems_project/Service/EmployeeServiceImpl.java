package com.example.ems_project.Service;

import com.example.ems_project.Exception.EmployeeNotFoundException;
import com.example.ems_project.Model.Employee;
import com.example.ems_project.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.ems_project.DTO.EmployeeDTO;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    //Constructor Injection

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository=employeeRepository;
        this.modelMapper=modelMapper;
    }

//    @Override
//    public Employee saveEmployee(Employee employee){
//        return this.employeeRepository.save(employee);
//    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
        Employee employee=mapToEntity(employeeDTO);
        Employee savedEmployee=employeeRepository.save(employee);

        return mapToDTO(savedEmployee);
    }

//    @Override
//    public List<Employee> getAllEmployees(){
//        return this.employeeRepository.findAll();
//    }

    @Override
    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employees=employeeRepository.findAll();

        return employees.stream().map(this::mapToDTO).toList();
    }

//    @Override
//    public Employee getEmployeeById(Long id){
//        return this.employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee not found with ID: "+id));
//        return this.employeeRepository.findById(id).orElseThrow(
//                ()->new EmployeeNotFoundException("Employee not found with ID: "+id));
//    }

    @Override
    public EmployeeDTO getEmployeeById(Long id){
        Employee employee=employeeRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Employee not found with Id: "+id)
        );

        return mapToDTO(employee);
    }



    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO){
//        Employee existing=getEmployeeById(id);

        Employee existing=employeeRepository.findById(id).orElseThrow(
                ()-> new EmployeeNotFoundException("Employee Not Found with id: "+id));

        existing.setFirstName(employeeDTO.getFirstName());
        existing.setLastName(employeeDTO.getLastName());
        existing.setEmailId(employeeDTO.getEmailId());
        existing.setDepartment(employeeDTO.getDepartment());

        Employee updatedEmployee=employeeRepository.save(existing);

        return mapToDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id){

//        this.employeeRepository.deleteById(id);

        Employee emp=employeeRepository.findById(id).orElseThrow(
                ()-> new EmployeeNotFoundException("Employee not found with id: "+id)
        );

        employeeRepository.delete(emp);
    }

    @Override
    public Page<EmployeeDTO> getEmployeesPagedSorted(int pageNo, int pageSize, String sortBy){
        Pageable pageable= PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Employee> employees=employeeRepository.findAll(pageable);

        return employees.map(this::mapToDTO);
    }

//    Add Mapping Methods

//    private EmployeeDTO mapToDTO(Employee employee){
//        EmployeeDTO dto=new EmployeeDTO();
//
//        dto.setId(employee.getId());
//        dto.setFirstName(employee.getFirstName());
//        dto.setLastName(employee.getLastName());
//        dto.setEmailId(employee.getEmailId());
//        dto.setDepartment(employee.getDepartment());
//
//        return dto;
//    }

    // Replacing above with below i.e., ModelMapper usage

    //    Convert Entity → DTO

    public EmployeeDTO mapToDTO(Employee employee){
        return modelMapper.map(employee, EmployeeDTO.class);
    }


//    private Employee mapToEntity(EmployeeDTO dto){
//        Employee employee=new Employee();
//
//        employee.setId(dto.getId());
//        employee.setFirstName(dto.getFirstName());
//        employee.setLastName(dto.getLastName());
//        employee.setEmailId(dto.getEmailId());
//        employee.setDepartment(dto.getDepartment());
//
//        return employee;
//    }

    // Replacing above with below i.e., ModelMapper usage

//    Convert DTO → Entity

    public Employee mapToEntity(EmployeeDTO employeeDTO){
        return modelMapper.map(employeeDTO, Employee.class);
    }


}
