package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

class GradeQueryServiceTest {
    DefaultGradeQueryService gradeQueryService = new DefaultGradeQueryService();
    CsvDataLoadService dataLoadService = new CsvDataLoadService();
    private Students csvStudents = CsvStudents.getInstance();
    Collection<Student> students;
    @BeforeEach
    void setUp(){
        dataLoadService.loadAndMerge();
        students = csvStudents.findAll();
    }

    @Test
    void getScoreByStudentName() {
        List<Score> filteredScore = gradeQueryService.getScoreByStudentName("A");
        Assertions.assertEquals(filteredScore.size(), 2);

        for(Score score:filteredScore){
            for(Student student:students){
                if (score.getStudentSeq() == student.getSeq()){
                    Assertions.assertTrue(student.getName().equals("A"));
                }
            }
        }
    }

    @Test
    void getScoreByStudentSeq() {
        Score filteredScore = gradeQueryService.getScoreByStudentSeq(1);
        Assertions.assertEquals(filteredScore.getScore(), 30);
    }
}