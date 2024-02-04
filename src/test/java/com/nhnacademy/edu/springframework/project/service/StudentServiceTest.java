package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class StudentServiceTest {
    @InjectMocks
    DefaultStudentService studentService;
    @Mock
    CsvStudents csvStudents;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getPassedStudents() {
        studentService.getPassedStudents();
        verify(csvStudents, times(1)).findAll();
    }

    @Test
    void getStudentsOrderByScore() {
        studentService.getStudentsOrderByScore();
        verify(csvStudents, times(1)).findAll();
    }
}