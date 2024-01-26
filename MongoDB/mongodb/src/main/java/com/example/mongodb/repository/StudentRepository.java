package com.example.mongodb.repository;

import com.example.mongodb.model.Student;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface StudentRepository extends ListCrudRepository<Student, String> {

    List<Student> findAllByName(String name);

    List<Student> findAllBySurname(String surname);

    List<Student> findAllByGrade(Integer grade);
}
