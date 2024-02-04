package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Component
public class CsvStudents implements Students {
    private List<Student> loadData;
    @Value("${studentFilePath}")
    private String filePath;

    private CsvStudents() {
        loadData = new ArrayList<>();
    }

    @Override
    public void load() {
        File csv = new File(filePath);
        BufferedReader br = null;
        String line;
        try{
            br = new BufferedReader(new FileReader(csv));
            List<Student> students = new ArrayList<>();
            while((line = br.readLine()) != null){
                String[] lineSplit = line.split(",");
                students.add(new Student(Integer.valueOf(lineSplit[0]), lineSplit[1]));
            }
            loadData = students;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(Objects.nonNull(br)){
                    br.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Collection<Student> findAll() {
        return loadData;
    }

    @Override
    public void merge( Collection<Score> scores) {
        for(Student student:loadData){
            int seq = student.getSeq();
            for(Score score:scores){
                if (score.getStudentSeq() == seq){
                    student.setScore(score);
                    break;
                }
            }
        }
    }
}
