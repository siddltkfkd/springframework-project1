package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class DefaultStudentService implements StudentService {
    @Autowired
    private CsvStudents csvStudents;
    @Override
    public Collection<Student> getPassedStudents() {
        Students studentRepository = csvStudents;
        return studentRepository.findAll().stream()
                .filter(student -> !student.getScore().isFail())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        Students studentRepository = csvStudents;
        return studentRepository.findAll().stream()
                .sorted(Comparator.comparing(student -> student.getScore().getScore()))
                .collect(Collectors.toList());
    }

}
