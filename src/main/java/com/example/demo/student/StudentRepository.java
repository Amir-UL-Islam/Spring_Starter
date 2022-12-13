package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Adding the Business Logic for Search and Validating Email and User
    // Find a User by Email
//    JDQL
//    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email); // This line will Transform into a SQL query (SELECT * FROM Student WHERE email = ?;)

}
