package backend;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import backend.Question.Difficulty;


/**
 * The GameCategory class represents a category used in actual instance of the game, holding an easy, medium, hard and expert question.
 * It is very similar to the Category class, except that it holds single questions instead of Lists.
 */
public class GameCategory {
    public String name;
    public Question easy;
    public Question medium;
    public Question hard;
    public Question expert;

    /**
     * Constructs a GameCategory from a Category object.
     * Randomly selects questions from each difficulty level and shuffles their options.
     *
     * @param category the Category object to create the GameCategory from
     */
    public GameCategory(Category category) {
        name = category.name;
        easy = Question.selectRandomQuestion(category.easy);
        medium = Question.selectRandomQuestion(category.medium);
        hard = Question.selectRandomQuestion(category.hard);
        expert = Question.selectRandomQuestion(category.expert);
        Collections.shuffle(easy.options);
        Collections.shuffle(medium.options);
        Collections.shuffle(hard.options);
        Collections.shuffle(expert.options);
    }

    /**
     * Removes a question of the specified difficulty.
     *
     * @param difficulty the difficulty of the question to remove
     * @return true if the question was successfully removed, false otherwise
     */
    public boolean removeQuestion(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                easy = null;
                return true;
            case MEDIUM:
                medium = null;
                return true;
            case HARD:
                hard = null;
                return true;
            case EXPERT:
                expert = null;
                return true;
            default:
                return false;
        }
    }

    /**
     * Checks if there are any questions left in the category.
     *
     * @return true if there are questions left, false otherwise
     */
    public boolean questionsLeft() {
        return (easy != null) || (medium != null) || (hard != null) || (expert != null);
    }

    /**
     * Gets the list of difficulties for which questions are still available in the category.
     *
     * @return a list of remaining difficulties
     */
    public List<Difficulty> getDifficultiesLeft() {
        List<Difficulty> left = new ArrayList<>();
        if (easy != null){
            left.add(Difficulty.EASY);
        }
        if (medium != null){
            left.add(Difficulty.MEDIUM);
        }
        if (hard != null){
            left.add(Difficulty.HARD);
        }
        if (expert != null){
            left.add(Difficulty.EXPERT);
        }
        return left;
    }

    /**
     * Retrieves a question based on the specified difficulty.
     *
     * @param difficulty the difficulty of the question to retrieve
     * @return the question of the specified difficulty, or null if no question is available for that difficulty
     */
    public Question getQuestionByDifficulty(Difficulty difficulty){
        if (difficulty == Difficulty.EASY){
            return easy;
        }
        else if (difficulty == Difficulty.MEDIUM){
            return medium;
        }
        else if (difficulty == Difficulty.HARD){
            return hard;
        }
        else if (difficulty == Difficulty.EXPERT){
            return expert;
        }
        else {
            return null;
        }
    }
}
