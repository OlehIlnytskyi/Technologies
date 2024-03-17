package com.example.postgres.repository;

import com.example.postgres.domain.Student;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.example.postgres.utils.TestUtils.*;

@Transactional
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@ExtendWith({SpringExtension.class})
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveMethod() {
        Student student = getTestStudent_A();

        Student actual = studentRepository.save(student);

        assertStudentsEquals(student, actual);
    }

    @Test
    void testSaveAllMethod() {
        List<Student> students = getTestListOfStudents();

        studentRepository.saveAll(students);

        assertEquals(students.size(), studentRepository.count());
    }

    @Test
    void testFindByIdMethod() {
        Student student = getTestStudent_B();
        studentRepository.save(student);

        Student actual = studentRepository.findById(student.getId())
                .orElseThrow();

        assertStudentsEquals(student, actual);
    }

    @Test
    void testFindAllMethod() {
        List<Student> students = getTestListOfStudents();
        studentRepository.saveAll(students);

        List<Student> actual = studentRepository.findAll();

        assertEquals(students.size(), actual.size());
    }

    @Test
    void testDeleteMethod() {
        Student student = getTestStudent_C();
        studentRepository.save(student);
        assertEquals(1, studentRepository.count());

        studentRepository.delete(student);

        assertEquals(0, studentRepository.count());
    }

    @Test
    void testDeleteAllMethod() {
        List<Student> students = getTestListOfStudents();
        studentRepository.saveAll(students);
        assertEquals(students.size(), studentRepository.count());

        studentRepository.deleteAll();

        assertEquals(0, studentRepository.count());
    }
}
