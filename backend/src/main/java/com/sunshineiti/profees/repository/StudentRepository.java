package com.sunshineiti.profees.repository;

import com.sunshineiti.profees.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);
    List<Student> findByMobile(String mobile);
    List<Student> findByAppNo(String appNo);

    @Query("SELECT s FROM Student s ORDER BY s.createdAt DESC")
    List<Student> findAllOrderByCreatedAtDesc();
}
