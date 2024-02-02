package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class StudentServiceTest {
    StudentService studentService = new DefaultStudentService();
    DataLoadService dataLoadService = new CsvDataLoadService();
    private Students csvStudents = CsvStudents.getInstance();
    Collection<Student> students;
    @BeforeEach
    void setUp(){
        dataLoadService.loadAndMerge();
        students = csvStudents.findAll();
    }
    @Test
    void getPassedStudents() {
        Collection<Student> passedStudents = studentService.getPassedStudents();
        Assertions.assertEquals(passedStudents.size(), 6);

        for (Student student:passedStudents){
            Assertions.assertFalse(student.getScore().isFail());
        }
    }

    @Test
    void getStudentsOrderByScore() {
        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        Assertions.assertEquals(orderedStudents.size(), 15);

        int prev = 0;
        int current = 0;
        for(Student student:orderedStudents){
            current = student.getScore().getScore();
            Assertions.assertTrue(prev<=current);
            prev = current;
        }
    }
}