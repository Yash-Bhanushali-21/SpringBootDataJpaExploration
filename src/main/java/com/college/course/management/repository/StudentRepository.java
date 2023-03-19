package com.college.course.management.repository;
import com.college.course.management.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    public List<Student> findByFirstName(String firstName);
    public List<Student> findByFirstNameContaining(String name);
    public List<Student> findByLastNameNotNull();
    List<Student> findByGuardianName(String name);
    /* Query annotation is based on class and not db schema/table
     naming nomenclature.**/

    //Query with JPQL.
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    //Query with JPQL.
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);
    //Query with native mySQL.
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )

    //Query with native mySQL with readable params..
    Student getStudentByEmailAddressNative(String emailId);
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(
            @Param("emailId") String emailId);

    /***
     * Query with native mySQL which updates the db.
     * viz. performs a transaction.
     * ideally, should be updated in the service layer of the code.
     */
    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName,String emailId);





}
