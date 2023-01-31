package com.springboot.microservice.Repository;

import com.springboot.microservice.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student getStudentByName(String name);

    Optional<Student> findByName(String name);
}
