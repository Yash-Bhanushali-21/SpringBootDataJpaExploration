package com.college.course.management.entity;

import jakarta.persistence.*;
import java.util.List;

import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "courses")

public class Teacher {

    @Id
    @SequenceGenerator(
            name="teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="teacher_sequence"
    )
    private long teacherId;
    private String firstName;
    private String lastName;


    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "teacher"
    )
    private List<Course> courses;




}
