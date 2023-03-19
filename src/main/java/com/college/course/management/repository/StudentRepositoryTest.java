package com.college.course.management.repository;
import com.college.course.management.entity.Guardian;
import com.college.course.management.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("yashbhadra1@gmail.com")
                .firstName("Yash")
                .lastName("Bhanushali")
              ///  .guardianName("Ketan")
               // .guardianEmail("ketan@gmail.com")
              //  .guardianMobile("999999999")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardianDetails() {
        Guardian guardian = Guardian.builder()
                .email("devbhadra1@gmail.com")
                .name("Dev")
                .mobileNumber("7777777777")
                .build();
        Student student = Student.builder()
                .emailId("shabbir@gmail.com")
                .firstName("shabbir")
                .lastName("ahluwalia")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);

    }

    @Test
    public void printStudentByFirstName() {
        List<Student> studentsWithFirstName = studentRepository.findByFirstName("shabbir");
        System.out.println(studentsWithFirstName);
    }
    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("sh");
        System.out.println(students);
    }
    @Test
    public void printStudentByGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("Dev");
        System.out.println(students);
    }

}
