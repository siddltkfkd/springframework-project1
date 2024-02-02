package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

class DataLoadServiceTest {

    @Test
    void loadAndMerge() {
        Students csvStudents = CsvStudents.getInstance();
        Collection<Student> students = csvStudents.findAll();
        Assertions.assertTrue(students.isEmpty());

        Scores csvScores = CsvScores.getInstance();
        List<Score> scores = csvScores.findAll();
        Assertions.assertTrue(scores.isEmpty());

        CsvDataLoadService csvDataLoadService = new CsvDataLoadService();
        csvDataLoadService.loadAndMerge();

        students = csvStudents.findAll();
        Assertions.assertFalse(students.isEmpty());

        scores = csvScores.findAll();
        Assertions.assertFalse(scores.isEmpty());

        for(Student student:students){
            Assertions.assertEquals(student.getScore().getStudentSeq(),student.getSeq());
        }
    }
}