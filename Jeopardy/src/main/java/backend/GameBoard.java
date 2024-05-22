package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import backend.Question.Difficulty;

/**
 * The GameBoard class manages the game categories and their questions.
 * It loads questions, shuffles categories, and provides methods to interact with the game board.
 */
public class GameBoard {
    DataHandler dataHandler;

    private List<GameCategory> gameCategories = new ArrayList<>();

    /**
     * Constructs a new GameBoard.
     * Loads questions using DataHandler, shuffles the categories, and initializes the game categories.
     */
    public GameBoard() {
        dataHandler = new DataHandler();
        dataHandler.loadQuestions();
        Collections.shuffle(dataHandler.Categories);
        int numberOfCategoriesInGame = 5;
        for (int i = 0; i < numberOfCategoriesInGame; i++){
            gameCategories.add(new GameCategory(dataHandler.Categories.get(i)));
        }
    }

    // TODO: so far unused - delete ?
    /**
     * Removes a question from a specified category based on its difficulty.
     *
     * @param indexCategory the index of the category from which to remove the question
     * @param difficulty the difficulty of the question that will be removed
     * @return true if the question was successfully removed
     */
    public boolean removeQuestion(int indexCategory, Difficulty difficulty) {
        gameCategories.get(indexCategory).removeQuestion(difficulty);
        return true;
    }

    // TODO: so far unused - delete ?
    /**
     * Retrieves the list of difficulties that still have questions left in a specified category.
     *
     * @param indexCategory the index of the category to check
     * @return a list of remaining difficulties in the specified category
     */
    public List<Difficulty> getDifficultiesLeftInGameCategory(int indexCategory) {
        return gameCategories.get(indexCategory).getDifficultiesLeft();
    }

    /**
     * Retrieves the list of game categories in the game board.
     *
     * @return a list of GameCategory objects
     */
    public List<GameCategory> getGameCategories() {
        return gameCategories;
    }
}

