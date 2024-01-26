package com.example.mongodb.repository;

import com.example.mongodb.config.MongoDBTestContainerConfig;
import com.example.mongodb.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Testcontainers
@ContextConfiguration(classes = MongoDBTestContainerConfig.class)
@EnableMongoRepositories
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void test() {
        Student student = getTestStudent_A();

        studentRepository.save(student);

        assertEquals(1, studentRepository.count());
    }

    public Student getTestStudent_A() {
        return Student.builder()
                .name("Student")
                .surname("A")
                .grade(3)
                .build();
    }
}
