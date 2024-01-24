package com.example.postgres.utils;

import com.example.postgres.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestUtils {

    public static void assertStudentsEquals(Student expected, Student actual) {
        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSurname(), actual.getSurname());
        assertEquals(expected.getGrade(), actual.getGrade());
    }

    public static Student getTestStudent_A() {
        return Student.builder()
                .name("Student")
                .surname("A")
                .grade(3)
                .build();
    }

    public static Student getTestStudent_B() {
        return Student.builder()
                .name("Student")
                .surname("B")
                .grade(4)
                .build();
    }

    public static Student getTestStudent_C() {
        return Student.builder()
                .name("Student")
                .surname("C")
                .grade(5)
                .build();
    }

    public static List<Student> getTestListOfStudents() {
        return List.of(
                getTestStudent_A(),
                getTestStudent_B(),
                getTestStudent_B()
        );
    }
}
