package com.nhnacademy.edu.springframework.project.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CsvScores implements Scores {
    private List<Score> loadData;
    @Value("${scoreFilePath}")
    private String filePath;

    private CsvScores(){
        loadData = new ArrayList<>();
    }

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
