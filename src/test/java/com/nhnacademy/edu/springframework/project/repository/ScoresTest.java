package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScoresTest {
    Scores csvScores = CsvScores.getInstance();

    @Test
    @Order(1)
    void load() {
        List<Score> scores = csvScores.findAll();
        Assertions.assertEquals(scores.size(),0);

        csvScores.load();
        scores = csvScores.findAll();
        Assertions.assertEquals(scores.size(), 15);

        csvScores.load();
        scores = csvScores.findAll();
        Assertions.assertEquals(scores.size(), 15);
    }

    @Test
    @Order(2)
    void findAll() {
        Collection<Score> scores = csvScores.findAll();
        Assertions.assertEquals(scores.stream().findAny().get().getClass(), Score.class);
    }

}