package com.springboot.microservice.Controller;

import com.springboot.microservice.Model.Student;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    List<Student> students = new ArrayList<Student>();

    @GetMapping
    public String returnValue() {
        return "This is Student MS";
    }

    @GetMapping(value = "/search")
    public Student getStudentById(@RequestParam int id) throws Exception {
        for (Student student : students) {
            if (id == student.getId()) {
                return student;
            }
        }
        return null;
    }

    @GetMapping(value = "/search/{name}")
    public Student getStudentByName(@PathVariable @NotNull String name) throws Exception {
        for (Student student : students) {
            if (name.equalsIgnoreCase(student.getName())) {
                return student;
            }
        }

        return null;
    }
    @GetMapping(value = "/search/all")
    public List<Student> getAllStudents(){
        return students;
    }

    @PostMapping(value = "/add")
    public String addNewStudent(@RequestBody Student s1) {
        Student student = new Student(s1.getId(), s1.getName(), s1.getClassNum());
        students.add(student);
        return "Student added successfully";
    }

    @PutMapping(value = "/update")
    public String updateStudentDetails(@RequestBody Student s1) {
        for (Student student : students) {
            if (student.getId() == s1.getId()) {
                BeanUtils.copyProperties(s1, student);
                return "Student details updated successfully";
            }
        }
        return null;
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteStudentById(@PathVariable int id) {
        for (Student student : students) {
            if (id == student.getId()) {
                students.remove(student);
                return student.getName() + " deleted successfully";
            }
        }
        return null;
    }

}
