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

import java.util.List;

import static com.example.mongodb.utils.TestsUtils.*;
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
    void testTransactionsWithCopyOfPrevTest() {
        Student student = getTestStudent_B();

        studentRepository.save(student);

        assertEquals(1, studentRepository.count());
    }

    @Test
    void testFindAllByNameWorks() {
        Student student_A = getTestStudent_A();
        Student student_B = getTestStudent_B();
        studentRepository.save(student_A);
        studentRepository.save(student_B);

        List<Student> actual = studentRepository.findAllByName(student_A.getName());

        assertEquals(1, actual.size());
    }

    @Test
    void testFindAllBySurnameWorks() {
        Student student_A = getTestStudent_A();
        Student student_B = getTestStudent_B();
        studentRepository.save(student_A);
        studentRepository.save(student_B);

        List<Student> actual = studentRepository.findAllBySurname(student_B.getSurname());

        assertEquals(1, actual.size());
    }

    @Test
    void testFindAllByGradeWorks() {
        Student student_A = getTestStudent_A();
        Student student_B = getTestStudent_B();
        studentRepository.save(student_A);
        studentRepository.save(student_B);

        List<Student> actual = studentRepository.findAllByGrade(student_A.getGrade());

        assertEquals(1, actual.size());
    }

    @Test
    void testDeleteMethodWorks() {
        Student student = getTestStudent_A();
        studentRepository.save(student);
        assertEquals(1, studentRepository.count());

        studentRepository.delete(student);

        assertEquals(0, studentRepository.count());
    }
}
