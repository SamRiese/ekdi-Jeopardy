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

/**
 * The ScoreHandler class manages the loading, saving, and sorting of player scores from a CSV file.
 */
public class ScoreHandler {
    List<Score> scores = new ArrayList<>();
    String path = "/data/scores.csv"; 

    /**
     * Loads scores from a CSV file and adds them to the scores list.
     * The scores are sorted after loading.
     */
    public void loadScores() {
        InputStream inputStream = ScoreHandler.class.getResourceAsStream(path); 
    
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] nameScoreValues = line.split(",|\n");
                scores.add(new Score(nameScoreValues[0], nameScoreValues[1]));
            }
            sortScores();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the scores to a CSV file.
     * The scores are sorted before saving.
     */
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

    /**
     * Adds a new score to the scores list and sorts the list.
     *
     * @param name the name of the player
     * @param score the score of the player
     */
    public void addScore(String name, int score) {
        scores.add(new Score(name, score));
        sortScores();
    }

    /**
     * Adds a new Score object to the scores list and sorts the list.
     *
     * @param newScore the Score object to add
     */
    public void addScore(Score newScore) {
        scores.add(newScore);
        sortScores();
    }

    /**
     * Sorts the scores list in descending order by score.
     */
    private void sortScores() {
        Collections.sort(scores, Collections.reverseOrder(Comparator.comparing(score -> score.score)));
    }

    /**
     * Converts the scores list to a CSV-formatted string.
     *
     * @return a string containing the scores in CSV format
     */
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
