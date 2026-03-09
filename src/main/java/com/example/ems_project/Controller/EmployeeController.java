package com.example.ems_project.Controller;

import com.example.ems_project.DTO.EmployeeDTO;
import com.example.ems_project.Model.Employee;
import com.example.ems_project.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*") // Allow React Front End later
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @PostMapping
    public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        return this.employeeService.saveEmployee(employeeDTO);
    }

    @GetMapping
    public List<EmployeeDTO> getAllTheEmployees(){
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id){
        return this.employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
        return this.employeeService.updateEmployee(id,employeeDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id){
        this.employeeService.deleteEmployee(id);
        return "Employee has been deleted successfully";
    }

    @GetMapping("/paged")
    public Page<EmployeeDTO> getEmployeesPaged(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        return this.employeeService.getEmployeesPagedSorted(pageNo, pageSize, sortBy);
    }
}
