package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
//@Component
//This Annotation will also work fine
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){ // I changed the method name getStudents to getStudent
    return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        System.out.println(student);
    }
}
// Passing the Values to Student Constructor