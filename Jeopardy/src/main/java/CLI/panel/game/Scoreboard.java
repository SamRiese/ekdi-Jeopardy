package CLI.panel.game;

import CLI.Theme;
import backend.Player;
import com.googlecode.lanterna.gui2.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Scoreboard is a panel that displays the scores of all players during the game.
 * It provides methods to create the scoreboard components and update player scores.
 */
public class Scoreboard extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    Game game;
    List<Label> playerScoreLabelList = new ArrayList<>();

    /**
     * Constructs a Scoreboard with the specified game.
     * Sets up the layout, theme, and initializes the scoreboard components.
     *
     * @param game the current game instance
     */
    protected Scoreboard(Game game) {
        super(new GridLayout(game.getNumberOfPlayers()));
        this.game = game;

        setTheme(Theme.getTheme());
        setLayoutData(layoutData);

        createScoreboardComponents();
    }

    /**
     * Creates the scoreboard components, including labels for player names and their scores.
     */
    private void createScoreboardComponents() {
        for (Player player : game.getPlayerList()) {
            addComponent(new Label(player.getName() + ":"));
        }

        for (Player player : game.getPlayerList()) {
            Label label = new Label(String.valueOf(player.getScore()));
            playerScoreLabelList.add(label);
            addComponent(label);
        }
    }

    /**
     * Updates the score display for a specific player.
     *
     * @param player the player whose score is to be updated
     */
    protected void updatePlayerScore(Player player) {
        for (int i = 0; i < game.getPlayerList().size() ; i++) {
            if (game.getPlayerList().get(i) == player) {
                playerScoreLabelList.get(i).setText(String.valueOf(player.getScore()));
            }
        }
    }
}
