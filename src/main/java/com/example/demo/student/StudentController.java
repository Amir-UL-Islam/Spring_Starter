package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;
import java.time.Month;

// This method Calls this Class as Rest Endpoint
@RestController
//	 REST EndPoint at localhost:8080/api/student
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;// The studentService will be automatically Instantiate by @Autowired Annotation

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()  // Getting the data
    public List<Student> getStudents(){
    return studentService.getStudent();
    }
    // Adding NEW Data/Service to The Application or Database

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) throws IllegalAccessException {
        studentService.addNewStudent(student);
    }
}
