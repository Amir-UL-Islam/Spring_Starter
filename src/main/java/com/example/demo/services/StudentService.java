package com.example.demo.services;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
            // TODO: SEND LOGE MESSAGE FOR SUCCESSFUL REGISTRATION
        }


        System.out.println(student);
    }

    public void deleteStudent(Long id) {
        boolean std = studentRepository.existsById(id);
        if (!std){
            throw new IllegalStateException("Student with ID " + id + " does not exists");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {

//        Name
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student with ID " + id + " does not exists"));
        if ((name != null) && (name.length() > 0) && !Objects.equals(student.getName(), name)){
//            If the name is not null and the name length is greater than 0 and the name is not equal to the name in the database
                student.setName(name);
        }



//        Email
        if ((email != null) && (email.length() > 0) && !Objects.equals(student.getEmail(), email)){
            // If the Updated Email is not the same as the Current Email
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("The Email is Already Updated");
            }else {
                student.setEmail(email);
            }
        }

    }
}