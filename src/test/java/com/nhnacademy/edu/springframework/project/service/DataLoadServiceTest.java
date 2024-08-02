package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DataLoadServiceTest {
    @InjectMocks
    CsvDataLoadService csvDataLoadService;
    @Mock
    CsvScores csvScores;
    @Mock
    CsvStudents csvStudents;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadAndMerge() {
        csvDataLoadService.loadAndMerge();
        verify(csvScores, times(1)).load();
        verify(csvStudents, times(1)).load();
        verify(csvStudents, times(1)).merge(any());
        verify(csvScores, times(1)).findAll();
    }
}