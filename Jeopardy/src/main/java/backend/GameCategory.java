package backend;

import java.util.List;
import java.util.ArrayList;

import backend.Question.Difficulty;

public class GameCategory {
    public String name;
    public Question easy;
    public Question medium;
    public Question hard;
    public Question expert;

    public GameCategory(Category category) {
        name = category.name;
        easy = Question.selectRandomQuestion(category.easy);
        medium = Question.selectRandomQuestion(category.medium);
        hard = Question.selectRandomQuestion(category.hard);
        expert = Question.selectRandomQuestion(category.expert);
    }
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
    public boolean questionsLeft() {
        return (easy != null) || (medium != null) || (hard != null) || (expert != null);
    }

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
}
