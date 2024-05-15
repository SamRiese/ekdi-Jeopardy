package CLI.panel.game;

import CLI.Theme;
import CLI.Window;
import backend.GameBoard;
import backend.GameCategory;
import backend.Player;
import backend.Question;
import com.googlecode.lanterna.gui2.*;

import java.util.List;

public class Game extends Panel {
    private static final String JEOPARDY =
            "       _                                _       \n" +
                    "      | |                              | |      \n" +
                    "      | | ___  ___  _ __   __ _ _ __ __| |_   _ \n" +
                    "  _   | |/ _ \\/ _ \\| '_ \\ / _` | '__/ _` | | | |\n" +
                    " | |__| |  __/ (_) | |_) | (_| | | | (_| | |_| |\n" +
                    "  \\____/ \\___|\\___/| .__/ \\__,_|_|  \\__,_|\\__, |\n" +
                    "                   | |                     __/ |\n" +
                    "                   |_|                    |___/";
    private static final int gameBoardHeight = 4;
    private static final int gameBoardWidth = 5;
    private static final int gameBoardSize = gameBoardHeight * gameBoardWidth;

    private final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    private final GameBoard gameBoardQuestions;
    private final Panel gameBoard;
    private final Window window;
    private final Scoreboard scoreboard;
    int availableQuestions = gameBoardSize;
    private final int numberOfPlayers;
    private final List<Player> playerList;
    private Player currentPlayer;
    private final Label currentTitleLabel;
    private int currentQuestionScore;


    public Game(Window window, List<Player> players) {
        super(new LinearLayout(Direction.VERTICAL));
        this.window = window;
        this.numberOfPlayers = players.size();
        this.playerList = players;
        this.currentPlayer = playerList.getFirst();
        this.scoreboard = new Scoreboard(this);
        this.gameBoardQuestions = new GameBoard();
        this.gameBoard = createGameBoard();
        this.currentTitleLabel = new Label(currentPlayer.getName() + " choose a question:").setLayoutData(layoutData);

        setTheme(Theme.getTheme());

        addComponent(new GameBoardMenuBarPanel(this));
        addComponent(new EmptySpace());
        addComponent(getCurrentTitleLabel());
        addComponent(new EmptySpace());
        addComponent(scoreboard);
        addComponent(new EmptySpace());
        addComponent(gameBoard);
    }

    protected Label getCurrentTitleLabel() {
        return currentTitleLabel;
    }

    protected void setCurrentTitleLabel(boolean title) {
        if (title) {
            currentTitleLabel.setText("JEOPARDY");
        } else {
            currentTitleLabel.setText(currentPlayer.getName() + " choose a question:");
        }
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
        for (Component component : gameBoard.getChildrenList()) {
            if (component instanceof Button) {
                ((Button) component).setEnabled(enable);
            }
        }
    }

    protected void validatePlayerAnswer(boolean isAnswerCorrect) {
        if (isAnswerCorrect) {
            currentPlayer.increaseScore(currentQuestionScore);
            scoreboard.updatePlayerScore(currentPlayer);
        } else {
            currentPlayer.increaseScore(-currentQuestionScore);
            scoreboard.updatePlayerScore(currentPlayer);
        }
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

    protected List<Player> getPlayerList() {
        return playerList;
    }

    protected int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    protected void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    protected Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    protected void setCurrentQuestionScore(int score) {
        this.currentQuestionScore = score;
    }

    protected Window getWindow() {
        return window;
    }
}
