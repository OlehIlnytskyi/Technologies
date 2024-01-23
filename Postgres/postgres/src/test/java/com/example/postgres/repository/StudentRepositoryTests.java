package com.example.postgres.repository;

import com.example.postgres.model.Student;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveMethod() {
        Student student = getTestStudent_A();

        studentRepository.save(student);

        Student result = studentRepository.findById(student.getId()).get();
        assertNotNull(result);
        assertEquals(student.getName(), result.getName());
        assertEquals(student.getSurname(), result.getSurname());
        assertEquals(student.getGrade(), result.getGrade());
    }

    private Student getTestStudent_A() {
        return Student.builder()
                .name("Student")
                .surname("A")
                .grade(3)
                .build();
    }
}
