package com.springboot.microservice.Controller;

import com.springboot.microservice.Model.Employee;
import com.springboot.microservice.Service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/search")
    public Optional<Employee> getEmployeeById(@RequestParam long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/list")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/add")
    public String createEmployeeRecord(@RequestBody Employee emp){
        employeeService.addEmployee(emp);
        return "Employee added successfully";
    }

    @PutMapping("/update")
    public String updateEmployeeDetails(@RequestBody Employee emp){
        if(employeeService.getEmployeeById(emp.getId()).isPresent()) {
            employeeService.updateEmployeeDetails(emp);
            return "Employee " + emp.getId() + " Details Updated Successfully!!";
        }
        return "Employee Details not found";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployeeRecord(@PathVariable long id){
        Optional<Employee> emp = employeeService.getEmployeeById(id);
        if(emp.isPresent()) {
            employeeService.deleteEmployeeById(id);
        }
        return "Employee Details not found";
    }

    @DeleteMapping("/delete/all")
    public String deleteAllEmployeeRecords(){
        employeeService.deleteAllEmployees();
        return "All employee records deleted successfully";
    }

    @PatchMapping("/update")
    public Employee updateEmployeeFirstName(@RequestParam Long id, @RequestParam String firstName) {
        Optional<Employee> emp = employeeService.getEmployeeById(id);
        if (emp.isPresent()) {
            employeeService.updateEmployeeName(id, firstName);
        }
        return null;
    }
}

