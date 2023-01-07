package com.example.demo.student;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity(name = "Student") // For Hibernate
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique",columnNames = "email")
                        }

)
public class Student {
    @Id
    // This SequenceGenerator is for Supporting the ID. Which is Bigint. And ID NEED to be auto INCREMENTED.
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1 // This 1 is to INCREMENT Id by 1. FOR EXAMPLE: 1, 2, 3, 4 ...
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, // Generating values for Sequence WE CREATED.
            generator = "student_sequence"
    )

    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "email",
//            unique = true,
            nullable = false
    )
    private String email;
    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private LocalDate dob;
    @Column(
            name = "age",
            nullable = false
    )
    @Transient
    private Integer age;

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student(Long id,
                   String name,
                   String email,
                   LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name,
                   String email,
                   LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

}
