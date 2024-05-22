package CLI.panel.game;

import CLI.Theme;
import backend.Player;
import com.googlecode.lanterna.gui2.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scoreboard extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    Game game;
    List<Label> playerScoreLabelList = new ArrayList<>();

    protected Scoreboard(Game game) {
        super(new GridLayout(game.getNumberOfPlayers()));
        this.game = game;

        setTheme(Theme.getTheme());
        setLayoutData(layoutData);

        createScoreboardComponents();
    }

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

    protected void updatePlayerScore(Player player) {
        for (int i = 0; i < game.getPlayerList().size() ; i++) {
            if (game.getPlayerList().get(i) == player) {
                playerScoreLabelList.get(i).setText(String.valueOf(player.getScore()));
            }
        }
    }
}
