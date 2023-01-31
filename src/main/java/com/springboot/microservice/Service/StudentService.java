package com.springboot.microservice.Service;

import com.springboot.microservice.Model.Student;
import com.springboot.microservice.Repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id){
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByName(String name){
        return studentRepository.findByName(name);
    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }
    public void updateStudentDetails(Student student){
        Optional<Student> oldStudent = studentRepository.findById(student.getId());
        if(oldStudent.isPresent()){
            BeanUtils.copyProperties(student,oldStudent);
        }

    }

    public void deleteStudentById(int id){
        studentRepository.deleteById(id);
    }

    public void deleteAllStudents(){
        studentRepository.deleteAll();
    }
}
