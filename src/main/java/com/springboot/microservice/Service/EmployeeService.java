package com.springboot.microservice.Service;

import com.springboot.microservice.Model.Employee;
import com.springboot.microservice.Repository.EmployeeRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRespository employeeRespository;

    public Optional<Employee> getEmployeeById(long id){
        return employeeRespository.findById(id);
    }

    public List<Employee> getAllEmployees(){
        return employeeRespository.findAll();
    }

    public void addEmployee(Employee employee){
        employeeRespository.save(employee);
    }

    public void updateEmployeeDetails(Employee employee){
        Optional<Employee> oldEmployee = employeeRespository.findById(employee.getId());
        if(oldEmployee.isPresent()){
            BeanUtils.copyProperties(employee,oldEmployee);
        }
    }

    public void deleteEmployeeById(long id){
        employeeRespository.deleteById(id);
    }

    public void deleteAllEmployees(){
        employeeRespository.deleteAll();
    }

    public void updateEmployeeName(long id,String name){
        Employee oldEmployee = employeeRespository.findEmployeeById(id);
        if(oldEmployee != null){
            oldEmployee.setFirstName(name);
        }

    }
}
