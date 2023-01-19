package com.springboot.microservice.Repository;

import com.springboot.microservice.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    public Student getStudentByName(String name);
}
