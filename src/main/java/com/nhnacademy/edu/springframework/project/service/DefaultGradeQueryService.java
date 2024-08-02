package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultGradeQueryService implements GradeQueryService {
    @Autowired
    private CsvStudents csvStudents;

    @Override
    public List<Score> getScoreByStudentName(String name) {
        List<Student> students = csvStudents.findAll().stream()
                .filter(student -> student.getName().equals(name))
                .collect(Collectors.toList());
        List<Score> scores = new ArrayList<>();
        for(Student student:students){
            scores.add(student.getScore());
        }
        return scores;
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        List<Student> students = csvStudents.findAll().stream()
                .filter(student -> student.getSeq() == seq)
                .collect(Collectors.toList());
        if (students.size() == 1){
            return students.get(0).getScore();
        }
        return null;
    }
}
