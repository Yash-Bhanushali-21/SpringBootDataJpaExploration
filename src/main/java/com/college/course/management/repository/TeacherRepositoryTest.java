package com.college.course.management.repository;

import com.college.course.management.entity.Course;
import com.college.course.management.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepo;

    @Test
    public void getTeachers() {
        System.out.println(teacherRepo.findAll());
    }
    @Test
    public void saveTeacher() {
        Course javaCourse = Course.builder()
                .title("Java")
                .credit(5)
                .build();
        Course dbaCourse = Course.builder()
                .title("DBA")
                .credit(5)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Test")
                .lastName("lastNameTest")
                .courses(List.of(dbaCourse,javaCourse))
                .build();

        teacherRepo.save(teacher);
    }

}
