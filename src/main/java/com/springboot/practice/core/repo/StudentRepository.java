package com.springboot.practice.core.repo;

import com.springboot.practice.core.vo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
