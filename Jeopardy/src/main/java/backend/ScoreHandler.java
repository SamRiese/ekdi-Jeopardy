package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ScoreHandler {
    List<Score> scores = new ArrayList<>();
    String path = "/data/scores.csv"; 

    public void loadScores() {
        
    
        InputStream inputStream = ScoreHandler.class.getResourceAsStream(path); 
    
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] nameScoreValues = line.split(",");
                scores.add(new Score(nameScoreValues[0], nameScoreValues[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeScores(){
        //TODO
    }
    public void addScore(String name, int score) {
        scores.add(new Score(name, score));
    }
    public void addScore(Score newScore) {
        scores.add(newScore);
    }

}
