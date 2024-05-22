package CLI.panel.game;

import CLI.Theme;
import CLI.panel.menu.MainMenuPanel;
import com.googlecode.lanterna.gui2.*;

public class GameBoardMenuBarPanel extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final Game game;

    protected GameBoardMenuBarPanel(Game game) {
        super(new GridLayout(3));
        this.game = game;

        setLayoutData(layoutData);
        setTheme(Theme.getTheme());

        addComponent(new Button("Exit", () -> game.getWindow().setComponent(new MainMenuPanel(game.getWindow()))));
        addComponent(new EmptySpace());
        addComponent(new Button("Save Scores and Exit", () -> game.getWindow().setComponent(
                new GameHighScorePanel(game.getWindow(), game.getPlayerList()))));
    }
}
