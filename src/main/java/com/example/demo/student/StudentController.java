package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;

// This method Calls this Class as Rest Endpoint
@RestController
//	 REST EndPoint at localhost:8080/api/student
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;// The studentService will be automatically Instantiate by @Autowired Annotation

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public List<Student> getStudents(){
    return studentService.getStudent();
    }
}
