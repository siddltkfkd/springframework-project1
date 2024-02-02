package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvDataLoadService implements DataLoadService {
    @Autowired
    private CsvScores csvScores;
    @Autowired
    private CsvStudents csvStudents;
    @Override
    public void loadAndMerge() {
        Scores scores = csvScores;
        scores.load();

        Students students = csvStudents;
        students.load();
        students.merge(scores.findAll());
    }
}
