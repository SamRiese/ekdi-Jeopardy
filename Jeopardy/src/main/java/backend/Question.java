package backend;

import java.util.List;
import java.util.Random;

public class Question {
    public String question;
    public List<String> options;
    public String answer;
    
    public static Question selectRandomQuestion(List<Question> questions) {
        int listSize = questions.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(listSize);
        return questions.get(randomIndex);
    }

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD,
        EXPERT
    }
    
}
