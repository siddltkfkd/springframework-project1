package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    @Test
    void load() {
        Scores csvScores = CsvScores.getInstance();
        csvScores.load();
        List<Score> scores = csvScores.findAll();
        Assertions.assertEquals(scores.size(), 15);
    }

    @Test
    void findAll() {
        Scores csvScores = CsvScores.getInstance();
        List<Score> scores = csvScores.findAll();
        Assertions.assertEquals(scores.size(),0);

        csvScores.load();
        scores = csvScores.findAll();
        Assertions.assertEquals(scores.size(), 15);
    }

}