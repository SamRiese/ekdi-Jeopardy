package backend;

import java.util.List;
import java.util.Random;

/**
 * The Question class represents a question, consists of the question itself, possible answers (options), and the correct answer.
 */
public class Question {
    public String question;
    public List<String> options;
    public String answer;
    
    /**
     * Selects a random question from a list of questions.
     *
     * @param questions the list of questions to select from
     * @return a randomly selected Question object from the list
     */
    public static Question selectRandomQuestion(List<Question> questions) {
        int listSize = questions.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(listSize);
        return questions.get(randomIndex);
    }
    
    /**
     * The Difficulty enum represents the difficulty levels of questions.
     */
    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD,
        EXPERT
    }
    
}
