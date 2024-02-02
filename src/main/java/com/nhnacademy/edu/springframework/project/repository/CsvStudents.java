package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class CsvStudents implements Students {
    private static final CsvStudents INSTANCE = new CsvStudents();
    private List<Student> loadData;
    private String filePath = "/home/nhnacademy/IdeaProjects/springframework-project1/src/main/resources/data/student.csv";

    private CsvStudents() {
        loadData = new ArrayList<>();
    }

    /** TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Students getInstance() {
        return INSTANCE;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
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

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
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
