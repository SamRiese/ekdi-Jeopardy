package backend;

import java.util.ArrayList;
import java.util.List;
import backend.Question.Difficulty;

public class GameBoard {
    DataHandler dataHandler;

    private List<GameCategory> gameCategories = new ArrayList<>();
    public GameBoard() {
        dataHandler = new DataHandler();
        dataHandler.loadQuestions();
        for(Category c: dataHandler.Categories) {
            gameCategories.add(new GameCategory(c));
        }
    }
    public boolean removeQuestion(int indexCategory, Difficulty difficulty) {
        gameCategories.get(indexCategory).removeQuestion(difficulty);
        return true;
    }
    public List<Difficulty> getDifficultiesLeftInGameCategory(int indexCategory) {
        return gameCategories.get(indexCategory).getDifficultiesLeft();
    }
}

