package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.net.URISyntaxException;
import java.net.URL;

public class ScoreHandler {
    List<Score> scores = new ArrayList<>();
    String path = "/data/scores.csv"; 

    public void loadScores() {
        InputStream inputStream = ScoreHandler.class.getResourceAsStream(path); 
    
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] nameScoreValues = line.split(",|\n");
                scores.add(new Score(nameScoreValues[0], nameScoreValues[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveScores(){
        URL resource = ScoreHandler.class.getResource(path);
        try {
            String pathToScores = Paths.get(resource.toURI()).toFile().getAbsolutePath();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToScores))) {
                writer.write(getScoresAsCSVString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sortScores();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void addScore(String name, int score) {
        scores.add(new Score(name, score));
        sortScores();
    }
    public void addScore(Score newScore) {
        scores.add(newScore);
        sortScores();
    }
    private void sortScores() {
        Collections.sort(scores, Collections.reverseOrder(Comparator.comparing(score -> score.score)));
    }
    private String getScoresAsCSVString() {
        String csvString = "";
        for (Score s: scores) {
            csvString += (s.name + "," + s.score + "\n");
        }
        return csvString;
    }

    public List<Score> getScores() {
        return scores;
    }
}
