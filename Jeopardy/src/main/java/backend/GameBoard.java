package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import backend.Question.Difficulty;

public class GameBoard {
    DataHandler dataHandler;

    private List<GameCategory> gameCategories = new ArrayList<>();
    public GameBoard() {
        dataHandler = new DataHandler();
        dataHandler.loadQuestions();
        Collections.shuffle(dataHandler.Categories);
        int numberOfCategoriesInGame = 5;
        for (int i = 0; i < numberOfCategoriesInGame; i++){
            gameCategories.add(new GameCategory(dataHandler.Categories.get(i)));
        }
    }
    public boolean removeQuestion(int indexCategory, Difficulty difficulty) {
        gameCategories.get(indexCategory).removeQuestion(difficulty);
        return true;
    }
    public List<Difficulty> getDifficultiesLeftInGameCategory(int indexCategory) {
        return gameCategories.get(indexCategory).getDifficultiesLeft();
    }
    public List<GameCategory> getGameCategories() {
        return gameCategories;
    }
}

