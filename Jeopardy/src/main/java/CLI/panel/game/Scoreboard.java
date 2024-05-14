package CLI.panel.game;

import CLI.Theme;
import com.googlecode.lanterna.gui2.*;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    Game game;
    List<Label> playerScoreLabelList = new ArrayList<>();

    protected Scoreboard(Game game) {
        super(new GridLayout(game.numberOfPlayers));
        this.game = game;

        setTheme(Theme.getTheme());
        setLayoutData(layoutData);

        createScoreboardComponents();
    }

    private void createScoreboardComponents() {
        for (int i = 1; i <= game.numberOfPlayers; i++) {
            addComponent(new Label("Player " + String.valueOf(i) + ":"));
        }

        for (int i = 0; i < game.numberOfPlayers; i++) {
            Label label = new Label(String.valueOf(0));
            playerScoreLabelList.add(label);
            addComponent(label);
        }
    }

    protected void updatePlayerScore(int score) {
        for (Label label : playerScoreLabelList) {
            label.setText(String.valueOf(score));
        }
    }
}
