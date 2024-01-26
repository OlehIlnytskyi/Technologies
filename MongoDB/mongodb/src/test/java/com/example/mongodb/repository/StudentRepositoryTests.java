package com.example.mongodb.repository;

import com.example.mongodb.config.TestsConfiguration;
import com.example.mongodb.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@DataMongoTest
@Testcontainers
@ContextConfiguration(classes = TestsConfiguration.class)
@EnableMongoRepositories(basePackages = "com.example.mongodb.repository")
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveMethodWorks() {
        Student student = getTestStudent_A();

        studentRepository.save(student);

        assertEquals(1, studentRepository.count());
    }

    @Test
    void testSaveMethodWorksAgain() {
        Student student = getTestStudent_B();

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

    public Student getTestStudent_B() {
        return Student.builder()
                .name("Student")
                .surname("B")
                .grade(4)
                .build();
    }
}
