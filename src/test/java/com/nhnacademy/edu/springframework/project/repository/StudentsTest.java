package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
    CsvScores csvScores = context.getBean("csvScores", CsvScores.class);
    CsvStudents csvStudents = context.getBean("csvStudents", CsvStudents.class);

    @Test
    @Order(1)
    void load() {
        Collection<Student> students = csvStudents.findAll();
        Assertions.assertEquals(students.size(), 0);

        csvStudents.load();
        students = csvStudents.findAll();
        Assertions.assertEquals(students.size(), 15);

        csvStudents.load();
        students = csvStudents.findAll();
        Assertions.assertEquals(students.size(), 15);
    }

    @Test
    @Order(2)
    void findAll() {
        csvStudents.load();
        Collection<Student> students = csvStudents.findAll();
        Assertions.assertEquals(students.stream().findAny().get().getClass(), Student.class);
    }

    @Test
    @Order(3)
    void merge() {
        for (Student student:csvStudents.findAll()){
            Assertions.assertNull(student.getScore());
        }

        csvScores.load();
        List<Score> scores = csvScores.findAll();
        csvStudents.merge(scores);

        for (Student student:csvStudents.findAll()){
            Assertions.assertNotNull(student.getScore());
            Assertions.assertEquals(student.getScore().getStudentSeq(), student.getSeq());
        }
    }
}