package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

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

    public void addNewStudent(Student student) throws IllegalAccessException {
        // Creating an Object
        Optional<Student> studentByEmail =  studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalAccessException("This Email Already Taken");
        }else {
            studentRepository.save(student);
        }


        System.out.println(student);
    }
}
// Passing the Values to Student Constructor