package com.springboot.microservice.Controller;

import com.springboot.microservice.Model.Student;
import com.springboot.microservice.Repository.StudentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    List<Student> students = new ArrayList<Student>();

    @GetMapping
    public String returnValue() {
        return "This is Student MS";
    }

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(value = "/search")
    public Optional<Student> getStudentById(@RequestParam int id) throws Exception {
//        for (Student student : students) {
//            if (id == student.getId()) {
//                return student;
//            }
//        }
        if(studentRepository.findById(id) != null){
            return studentRepository.findById(id);
        }
        return null;
    }

    @GetMapping(value = "/search/{name}")
    public Student getStudentByName(@PathVariable @NotNull String name) throws Exception {
//        for (Student student : students) {
//            if (name.equalsIgnoreCase(student.getName())) {
//                return student;
//            }
//        }
        Student st = studentRepository.getStudentByName(name);
        if(st != null){
            return st;
        }
        return null;
    }
    @GetMapping(value = "/search/all")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @PostMapping(value = "/add")
    public String addNewStudent(@RequestBody Student s1) {
//        Student student = new Student(s1.getId(), s1.getName(), s1.getClassNum());
//        students.add(student);
          studentRepository.save(s1);
        return "Student added successfully";
    }

    @PutMapping(value = "/update")
    public String updateStudentDetails(@RequestBody Student s1) {
//        for (Student student : students) {
//            if (student.getId() == s1.getId()) {
//                BeanUtils.copyProperties(s1, student);
//                return "Student details updated successfully";
//            }
//        }
        Optional<Student> st = studentRepository.findById(s1.getId());
        if(st != null ){
            BeanUtils.copyProperties(s1,st);
            return "Student "+s1.getName()+"details updated successfully";
        }

        return null;
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteStudentById(@PathVariable int id) {
//        for (Student student : students) {
//            if (id == student.getId()) {
//                students.remove(student);
//                return student.getName() + " deleted successfully";
//            }
//        }
        Optional<Student> s = studentRepository.findById(id);
        if(s != null){
            studentRepository.deleteById(id);
            return "Student "+id+ " deleted successfully";
        }

        return null;
    }

}
