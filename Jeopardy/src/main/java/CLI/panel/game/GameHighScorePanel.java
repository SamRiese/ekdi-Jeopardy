package CLI.panel.game;

import CLI.Theme;
import CLI.Window;
import CLI.panel.menu.MainMenuPanel;
import backend.Player;
import backend.ScoreHandler;
import com.googlecode.lanterna.gui2.*;

import java.util.List;

/**
 * GameHighScorePanel is a panel that displays the high scores at the end of the game.
 * It provides options to save the scores and return to the main menu.
 */
public class GameHighScorePanel extends Panel {

    private static final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    private final Window window;
    private final List<Player> playerList;

    /**
     * Constructs a GameHighScorePanel with the specified window and player list.
     * Sets up the layout, theme, and components to display scores and menu options.
     *
     * @param window the application window
     * @param playerList the list of players and their scores
     */
    protected GameHighScorePanel(Window window, List<Player> playerList) {
        super(new LinearLayout(Direction.VERTICAL));
        this.window = window;
        this.playerList = playerList;

        setTheme(Theme.getTheme());

        addComponent(new Label("Scores").setLayoutData(layoutData));
        addComponent(new EmptySpace());
        addComponent(createScoreBoard());
        addComponent(new EmptySpace());
        addComponent(menuPanel());
    }


    /**
     * Creates a panel that displays the ranking list of players.
     *
     * @return a panel with the player scores
     */
    private Panel createScoreBoard() {
        Panel panel = new Panel(new GridLayout(1)).setLayoutData(layoutData);
        int counter = 1;

        playerList.sort((p1, p2) -> Integer.compare(p1.getScore(), p2.getScore()));

        for (Player player : playerList.reversed()) {
            panel.addComponent(new Label(counter + ") " + player.getName() + ": " + player.getScore()).setLayoutData(layoutData));
            counter++;
        }
        return panel;
    }

    /**
     * Creates a panel with buttons for saving scores and exiting to the main menu.
     *
     * @return a panel with menu buttons
     */
    private Panel menuPanel() {
        Panel panel = new Panel(new GridLayout(3)).setLayoutData(layoutData);

        Button saveButton = new Button("Save Scores");
        saveButton.addListener(button -> {
            saveScores();
            button.setEnabled(false);
            button.setVisible(false);
        });

        panel.addComponent(saveButton);
        panel.addComponent(new EmptySpace());
        panel.addComponent(new Button("Exit", () -> window.setComponent(new MainMenuPanel(window))));
        return panel;
    }

    /**
     * Saves the scores of all players using the ScoreHandler.
     */
    private void saveScores() {
        ScoreHandler scoreHandler = new ScoreHandler();
        scoreHandler.loadScores();

        for (Player player : playerList) {
            scoreHandler.addScore(player.getName(), player.getScore());
        }
        scoreHandler.saveScores();
    }
}
