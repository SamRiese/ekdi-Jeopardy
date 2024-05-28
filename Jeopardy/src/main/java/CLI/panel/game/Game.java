package CLI.panel.game;

import CLI.Theme;
import CLI.Window;
import backend.GameBoard;
import backend.GameCategory;
import backend.Player;
import backend.Question;
import com.googlecode.lanterna.gui2.*;

import java.util.List;

/**
 * This class is a panel representing the game for the CLI application.
 * It displays the game board, allows players to choose questions, and handles game logic.
 */
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
    private static final int numberOfCategories = 5;
    private int roundsLeft = 20;
    private static final int gameBoardWidth = (numberOfCategories + (numberOfCategories - 1));

    private final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    private final GameBoard gameBoardQuestions;
    private final Panel gameBoard;
    private final Window window;
    private final Scoreboard scoreboard;
    private final int numberOfPlayers;
    private final List<Player> playerList;
    private Player currentPlayer;
    private Player questionChooserPlayer;
    private final Label currentTitleLabel;
    private int currentQuestionScore;
    Label roundsLeftLabel;

    /**
     * Constructs a Game panel with the specified window and list of players.
     * Sets up the game board, scoreboard, and other components.
     *
     * @param window the main application window
     * @param players the list of players participating in the game
     */
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
        this.roundsLeftLabel = (new Label("Rounds left:"+roundsLeft).setLayoutData(layoutData));

        this.questionChooserPlayer = playerList.getFirst();

        setTheme(Theme.getTheme());
        addComponent(new GameBoardMenuBarPanel(this));
        addComponent(new EmptySpace());
        addComponent(getCurrentTitleLabel());
        addComponent(new EmptySpace());
        addComponent(scoreboard);
        addComponent(new EmptySpace());
        addComponent(roundsLeftLabel);
        addComponent(new EmptySpace());
        addComponent(gameBoard);
    }

    /**
     * Gets the current title label.
     *
     * @return the current title label
     */
    protected Label getCurrentTitleLabel() {
        return currentTitleLabel;
    }


    /**
     * Sets the current title label text.
     *
     * @param title if true, sets the label to "JEOPARDY"; otherwise, prompts the next player to choose a question
     */
    protected void setCurrentTitleLabel(boolean title) {
        if (title) {
            currentTitleLabel.setText("JEOPARDY");
        } else {
            int index = playerList.indexOf(questionChooserPlayer) + 1;
            Player player;
            if (index < playerList.size()) {
                player = playerList.get(index);
            } else {
                player = playerList.getFirst();
            }
            questionChooserPlayer = player;
            currentTitleLabel.setText(player.getName() + " choose a question:");
        }
    }


    /**
     * Creates the game board panel with categories and questions.
     *
     * @return the game board panel
     */
    protected Panel createGameBoard() {
        Panel gameBoard = new Panel(new GridLayout(gameBoardWidth)).setLayoutData(layoutData);
        int width = 1;

        for (GameCategory gc : this.gameBoardQuestions.getGameCategories()) {
            gameBoard.addComponent(new Label(gc.name));
            if (width < numberOfCategories) {
                gameBoard.addComponent(new EmptySpace());
            }
            width++;
        }

        int label = 100;
        for (Question.Difficulty difficulty : Question.Difficulty.values()) {
            width = 1;
            for (GameCategory gc : this.gameBoardQuestions.getGameCategories()) {
                gameBoard.addComponent(new GameBoardButton(String.valueOf(label), this, gc.getQuestionByDifficulty(difficulty)));
                if (width < numberOfCategories) {
                    gameBoard.addComponent(new EmptySpace());
                }
                width++;
            }
            label += 100;
        }
        return gameBoard;
    }

    /**
     * Enables or disables the buttons on the game board.
     *
     * @param enable if true, enables the buttons; otherwise, disables them
     */
    protected void enableButtons(boolean enable) {
        gameBoard.getChildrenList()
                .stream()
                .filter(component -> component instanceof Button)
                .map(component -> (Button) component)
                .forEach(button -> button.setEnabled(enable));
    }

    /**
     * Checks if any game board buttons are available (visible).
     *
     * @return true if there are available buttons; otherwise, false
     */
    protected boolean gameBoardButtonsAvailable() {
        return gameBoard.getChildrenList()
                .stream()
                .filter(component -> component instanceof Button)
                .map(component -> (Button) component)
                .anyMatch(Button::isVisible);
    }

    /**
     * Validates the player's answer and updates the score accordingly.
     * If the answer is correct, increases the player's score; otherwise, decreases it.
     * If no more questions are available, transitions to the high score panel.
     *
     * @param isAnswerCorrect if true, the answer is correct; otherwise, it is incorrect
     */
    protected void validatePlayerAnswer(boolean isAnswerCorrect) {
        int factor = 1;
        if (roundsLeft <= 4) {
            factor = 2;
        }
        if (isAnswerCorrect) {
            currentPlayer.increaseScore(currentQuestionScore * factor);
            scoreboard.updatePlayerScore(currentPlayer);
        } else {
            currentPlayer.increaseScore((-currentQuestionScore/2) * factor);
            scoreboard.updatePlayerScore(currentPlayer);
        }

        if (!gameBoardButtonsAvailable()){
            window.setComponent(new GameHighScorePanel(window, playerList));
        }
    }

    /**
     * Gets the list of players.
     *
     * @return the list of players
     */
    protected List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Gets the number of players.
     *
     * @return the number of players
     */
    protected int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Sets the current player.
     *
     * @param currentPlayer the current player
     */
    protected void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Gets the current player.
     *
     * @return the current player
     */
    protected Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Sets the current question score.
     *
     * @param score the current question score
     */
    protected void setCurrentQuestionScore(int score) {
        this.currentQuestionScore = score;
    }

    /**
     * Gets the main application window.
     *
     * @return the main application window
     */
    protected Window getWindow() {
        return window;
    }

    /**
     * Decrements rounds left.
     */
    public void decrementRoundsLeft() {
        roundsLeft--;
        roundsLeftLabel.setText("Rounds left: " + roundsLeft);
    }
}
