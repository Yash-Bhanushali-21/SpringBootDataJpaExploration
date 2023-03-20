package com.college.course.management.repository;

import com.college.course.management.entity.Course;
import com.college.course.management.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepo;

    @Test
    public void getTeachers() {
        List<Teacher> teacherList = teacherRepo.findAll();
        System.out.println(teacherList);
    }
    @Test
    public void saveTeacher() {
        Course javaCourse = Course.builder()
                .title("Hibernate")
                .credit(5)
                .build();
        Course dbaCourse = Course.builder()
                .title("Spring")
                .credit(5)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Telusko")
                .lastName("Learnings")
                .courses(List.of(dbaCourse,javaCourse))
                .build();

        teacherRepo.save(teacher);
    }

    @Test
    public void addCourse() {
        Course c = Course.builder()
                  .title("AssociationCourse")
                .credit(5)
                .build();
        Teacher t= Teacher.builder()
                 .firstName("AssociationTeacher")
                .lastName("AssociationTeach")
                .build();
        List <Course> courses = new ArrayList<Course>();
        courses.add(c);

        t.setCourses(courses);
        c.setTeacher(t);

        teacherRepo.save(t);


    }

}
