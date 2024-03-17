package com.example.postgres.repository;

import com.example.postgres.domain.Student;
import org.springframework.data.repository.ListCrudRepository;


public interface StudentRepository extends ListCrudRepository<Student, Long> {

}
