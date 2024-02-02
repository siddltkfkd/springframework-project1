package com.nhnacademy.edu.springframework.project.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvScores implements Scores {
    private static final CsvScores INSTANCE = new CsvScores();
    private List<Score> loadData;
    private String filePath = "/home/nhnacademy/IdeaProjects/springframework-project1/src/main/resources/data/score.csv";

    private CsvScores(){
        loadData = new ArrayList<>();
    }

    /** TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Scores getInstance() {
        return INSTANCE;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        File csv = new File(filePath);
        BufferedReader br = null;
        String line;
        try{
            br = new BufferedReader(new FileReader(csv));
            List<Score> scores = new ArrayList<>();
            while((line = br.readLine()) != null){
                String[] lineSplit = line.split(",");
                scores.add(new Score(Integer.valueOf(lineSplit[0]), Integer.valueOf(lineSplit[1])));
            }
            loadData = scores;
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
    public List<Score> findAll() {
        return loadData;
    }
}
