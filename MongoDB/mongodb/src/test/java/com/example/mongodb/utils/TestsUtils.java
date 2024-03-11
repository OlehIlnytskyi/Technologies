package com.example.mongodb.utils;

import com.example.mongodb.model.Student;

public class TestsUtils {

    public static Student getTestStudent_A() {
        return Student.builder()
                .name("Name_1")
                .surname("A")
                .grade(3)
                .build();
    }

    public static Student getTestStudent_B() {
        return Student.builder()
                .name("Name_2")
                .surname("B")
                .grade(4)
                .build();
    }
}
