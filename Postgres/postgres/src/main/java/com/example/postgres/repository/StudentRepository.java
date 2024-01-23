package com.example.postgres.repository;

import com.example.postgres.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends Repository<Student, Long> {

    Student save(Student student);

    List<Student> findAll();

    Optional<Student> findById(Long id);

    void delete(Student student);

    void deleteById(Long id);
}
