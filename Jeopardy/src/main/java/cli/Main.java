package cli;

import cli.menu.MainMenu;
import backend.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.removeQuestion(1, Question.Difficulty.MEDIUM);
        List<Question.Difficulty> left = gameBoard.getDifficultiesLeftInGameCategory(1);
        MainMenu.run();
    }

}
