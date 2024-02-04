package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScoresTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
    CsvScores csvScores = context.getBean("csvScores", CsvScores.class);
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
        csvScores.load();
        Collection<Score> scores = csvScores.findAll();
        Assertions.assertEquals(scores.stream().findAny().get().getClass(), Score.class);
    }

}