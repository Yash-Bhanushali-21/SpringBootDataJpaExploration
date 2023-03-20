package com.college.course.management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @SequenceGenerator(name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    /***
     * course material cannot exist w/o a course.
     * it has to have a course.
     * also, one course will exactly have one type
     * of course material. thus 1-1mapping.
     * **/
    @OneToOne(
            /**cascade type as we do want to create a course material
             * if corresponding course is not there by creating a course first.**/
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "course_id",
            /**
             * refeferences ot courseId in course.*/
            referencedColumnName = "courseId"
    )
    private Course course;

}

