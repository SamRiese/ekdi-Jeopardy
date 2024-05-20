package CLI.panel.game;

import CLI.Theme;
import CLI.Window;
import CLI.panel.menu.MainMenuPanel;
import backend.Player;
import backend.ScoreHandler;
import com.googlecode.lanterna.gui2.*;

import java.util.List;

public class GameHighScorePanel extends Panel {

    private static final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    private final Window window;
    private final List<Player> playerList;

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

    private void saveScores() {
        ScoreHandler scoreHandler = new ScoreHandler();
        scoreHandler.loadScores();

        for (Player player : playerList) {
            scoreHandler.addScore(player.getName(), player.getScore());
        }
        scoreHandler.saveScores();
    }
}
