package com.college.course.management.repository;

import com.college.course.management.entity.Course;
import com.college.course.management.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Ketan")
                .lastName("Bhanushali")
                .build();
        Course course = Course.builder()
                .title("MachineLearning")
                .credit(6)
               .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0,3);
        Pageable secondPageWithThreeRecords = PageRequest.of(1,3);

        List<Course> courses = courseRepository.findAll(secondPageWithThreeRecords).getContent();
        System.out.println("courses=" + courses);
        Long totalElements = courseRepository.findAll(secondPageWithThreeRecords).getTotalElements();
        Integer totalPages =  courseRepository.findAll(secondPageWithThreeRecords).getTotalPages();

        System.out.println("totalElements=" + totalElements);
        System.out.println("totalPages=" + totalElements);
        System.out.println("courses=" + courses);

    }
    @Test
    public void findAllPaginationWithSorting() {
        Pageable sortByTitle = PageRequest.of(
                0,
                2,
                Sort.by("title")
        );
        Pageable sortByCreditDesc = PageRequest.of(
                0,
                2,
                Sort.by("credit").descending()
        );

        Pageable sortByTitleAndCreditDesc = PageRequest.of(
                0,
                2,
                Sort.by("title")
                        .descending()
                        .and(Sort.by("credit"))
        );

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        System.out.println("courses=" + courses);


    }

    @Test
    public void printFindByTitleContaining() {
        Pageable firstPageTenRecords = PageRequest.of(
                0,10
        );
        List<Course> courses = courseRepository.findByTitleContaining(
                "D",
                firstPageTenRecords
        );
        System.out.println("courses=" + courses);
    }


}
