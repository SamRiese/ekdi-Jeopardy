package CLI.panel.game;

import CLI.Theme;
import CLI.Window;
import backend.GameBoard;
import backend.GameCategory;
import backend.Question;
import com.googlecode.lanterna.gui2.*;

public class Game extends Panel {
    protected static final String JEOPARDY =
            "       _                                _       \n" +
                    "      | |                              | |      \n" +
                    "      | | ___  ___  _ __   __ _ _ __ __| |_   _ \n" +
                    "  _   | |/ _ \\/ _ \\| '_ \\ / _` | '__/ _` | | | |\n" +
                    " | |__| |  __/ (_) | |_) | (_| | | | (_| | |_| |\n" +
                    "  \\____/ \\___|\\___/| .__/ \\__,_|_|  \\__,_|\\__, |\n" +
                    "                   | |                     __/ |\n" +
                    "                   |_|                    |___/";
    protected static final int gameBoardHeight = 4;
    protected static final int gameBoardWidth = 5;
    protected static final int gameBoardSize = gameBoardHeight * gameBoardWidth;

    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final GameBoard gameBoardQuestions;
    Panel gameBoard;
    CLI.Window window;
    Scoreboard scoreboard;
    int availableQuestions = gameBoardSize;
    final int numberOfPlayers;
    int currentPlayer;


    public Game(Window window, int numberOfPlayers) {
        super(new LinearLayout(Direction.VERTICAL));
        this.window = window;
        this.numberOfPlayers = numberOfPlayers;
        this.scoreboard = new Scoreboard(this);
        this.gameBoardQuestions = new GameBoard();
        this.gameBoard = createGameBoard();

        setTheme(Theme.getTheme());

        addComponent(new GameBoardMenuBarPanel(this));
        addComponent(new EmptySpace());
        addComponent(getTitleAsLabel());
        addComponent(new EmptySpace());
        addComponent(scoreboard);
        addComponent(new EmptySpace());
        addComponent(gameBoard);
    }

    protected Label getTitleAsLabel() {
//        return new Label(JEOPARDY).setLayoutData(layoutData);
        return new Label("JEOPARDY").setLayoutData(layoutData);
    }

    protected Panel createGameBoard() {
        Panel gameBoard = new Panel(new GridLayout(gameBoardWidth)).setLayoutData(layoutData);

        int label = 100;
        for (Question.Difficulty difficulty : Question.Difficulty.values()) {
            for (GameCategory gc : this.gameBoardQuestions.getGameCategories()) {
                gameBoard.addComponent(new GameBoardButton(String.valueOf(label), this, gc.getQuestionByDifficulty(difficulty)));
            }
            label += 100;
        }

        return gameBoard;
    }

    protected void enableButtons(boolean enable) {
        for (Component panel : gameBoard.getChildrenList()) {
            if (panel instanceof Panel) {
                for (Component button : ((Panel) panel).getChildrenList()) {
                    if (button instanceof Button) {
                        ((Button) button).setEnabled(enable);
                    }
                }
            }
        }
    }

    protected void selectCurrentPlayer() {
//        System.out.println("Select current player");
    }

    protected void validatePlayerAnswer(boolean isAnswerCorrect) {
    //        System.out.println("validatePlayerAnswer");
    }

    protected Panel getScoreboard() {
        Panel scoreboard = new Panel(new GridLayout(numberOfPlayers)).setLayoutData(layoutData);

        for (int i = 1; i <= numberOfPlayers; i++) {
            scoreboard.addComponent(new Label("Player " + String.valueOf(i) + ":"));
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            scoreboard.addComponent(new Label(String.valueOf(i)));
        }

        return scoreboard;
    }
}
