package com.example.mongodb.repository;

import com.example.mongodb.model.Student;
import org.springframework.data.repository.ListCrudRepository;

public interface StudentRepository extends ListCrudRepository<Student, String> {

}
