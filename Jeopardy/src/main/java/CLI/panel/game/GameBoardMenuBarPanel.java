package CLI.panel.game;

import CLI.Theme;
import CLI.panel.menu.MainMenuPanel;
import com.googlecode.lanterna.gui2.*;

/**
 * GameBoardMenuBarPanel is a panel that contains the menu bar for the game board.
 * It provides buttons to exit the game or save the scores.
 */
public class GameBoardMenuBarPanel extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final Game game;

    /**
     * Constructs a GameBoardMenuBarPanel with the specified game.
     * Sets up the layout, theme, and menu buttons.
     *
     * @param game the current game instance
     */
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
