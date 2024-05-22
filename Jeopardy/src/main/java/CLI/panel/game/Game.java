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
    private static final int numberOfCategories = 5;
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
        this.questionChooserPlayer = playerList.getFirst();

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

    protected void enableButtons(boolean enable) {
        gameBoard.getChildrenList()
                .stream()
                .filter(component -> component instanceof Button)
                .map(component -> (Button) component)
                .forEach(button -> button.setEnabled(enable));
    }

    protected boolean gameBoardButtonsAvailable() {
        return gameBoard.getChildrenList()
                .stream()
                .filter(component -> component instanceof Button)
                .map(component -> (Button) component)
                .anyMatch(Button::isVisible);
    }

    protected void validatePlayerAnswer(boolean isAnswerCorrect) {
        if (isAnswerCorrect) {
            currentPlayer.increaseScore(currentQuestionScore);
            scoreboard.updatePlayerScore(currentPlayer);
        } else {
            currentPlayer.increaseScore(-currentQuestionScore/2);
            scoreboard.updatePlayerScore(currentPlayer);
        }

        if (!gameBoardButtonsAvailable()){
            window.setComponent(new GameHighScorePanel(window, playerList));
        }
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
