package com.springboot.microservice.Controller;

import com.springboot.microservice.Model.Student;
import com.springboot.microservice.Service.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping
    public String returnValue() {
        return "This is Student MS";
    }

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/search")
    public Optional<Student> getStudentById(@RequestParam int id){
        if(studentService.getStudentById(id).isPresent()){
            return studentService.getStudentById(id);
        }
        return Optional.empty();
    }

    @GetMapping(value = "/search/{name}")
    public Optional<Student> getStudentByName(@PathVariable @NotNull String name){
        Optional<Student> st = studentService.getStudentByName(name);
        if(st.isPresent())  return st;
        return Optional.empty();
    }
    @GetMapping(value = "/search/all")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping(value = "/add")
    public String addNewStudent(@RequestBody Student s1) {
          studentService.addStudent(s1);
        return "Student added successfully";
    }

    @PutMapping(value = "/update")
    public String updateStudentDetails(@RequestBody Student s1) {
        Optional<Student> st = studentService.getStudentById(s1.getId());
        if(st.isPresent()){
            BeanUtils.copyProperties(s1,st);
            return "Student "+s1.getName()+"details updated successfully";
        }

        return null;
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteStudentById(@PathVariable int id) {
        Optional<Student> s = studentService.getStudentById(id);
        if(s.isPresent()){
            studentService.deleteStudentById(id);
            return "Student "+id+ " deleted successfully";
        }

        return null;
    }

}
