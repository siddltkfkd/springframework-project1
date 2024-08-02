package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class GradeQueryServiceTest {
    @Mock
    CsvStudents csvStudents;
    @InjectMocks
    DefaultGradeQueryService gradeQueryService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getScoreByStudentName() {
        gradeQueryService.getScoreByStudentName("A");
        verify(csvStudents, times(1)).findAll();
    }

    @Test
    void getScoreByStudentSeq() {
        gradeQueryService.getScoreByStudentSeq(1);
        verify(csvStudents, times(1)).findAll();
    }
}