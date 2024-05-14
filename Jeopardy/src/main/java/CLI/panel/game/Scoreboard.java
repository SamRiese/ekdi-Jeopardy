package CLI.panel.game;

import CLI.Theme;
import backend.Player;
import com.googlecode.lanterna.gui2.*;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    Game game;
    List<Label> playerNameLabelList = new ArrayList<>();
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
            Label label = new Label(player.getName());
            playerNameLabelList.add(label);
            addComponent(label);
        }

        for (Player player : game.getPlayerList()) {
            Label label = new Label(String.valueOf(player.getScore()));
            playerScoreLabelList.add(label);
            addComponent(label);
        }
    }

    protected void updatePlayerScore(Player player) {
        for (Label label : playerNameLabelList) {
            label.setText(String.valueOf(player.getScore()));
        }
    }
}
