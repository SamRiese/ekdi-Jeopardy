package CLI.panel.menu;

import CLI.Window;
import CLI.panel.game.Game;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Label;

/**
 * GameSetupPanel is a panel for setting up a game by choosing the number of players.
 * It extends the BaseMenuPanel and customizes the title and action list box.
 */
public class GameSetupPanel extends BaseMenuPanel {

    /**
     * Constructs a GameSetupPanel.
     * adds an extra exit button, and provides options for selecting the number of players.
     *
     * @param window the main application window
     */
    public GameSetupPanel(Window window) {
        super(window);
        addComponent(new EmptySpace());
        addComponent(
                new Button("Exit", () -> window.setComponent(new MainMenuPanel(window))).setLayoutData(layoutData)
        );
    }

    /**
     * Creates a Label component for the panel's title.
     * Overrides the method from BaseMenuPanel to provide a specific title for this panel.
     *
     * @return a Label with the title "Choose Number of Players:"
     */
    @Override
    protected Label getTitleAsLabel() {
        return new Label("Choose Number of Players:").setLayoutData(layoutData);
    }

    /**
     * Creates an ActionListBox component for the panel.
     * Overrides the method from BaseMenuPanel to provide options for selecting the number of players.
     *
     * @return a new ActionListBox to define the number of players from 1 to 6
     */
    @Override
    protected ActionListBox getActionListBox() {
        ActionListBox actionListBox = new ActionListBox().setLayoutData(layoutData);

        for (int i = 1; i <= 6; i++) {
            int numberOfPlayers = i;
            actionListBox.addItem(String.valueOf(i), () -> window.setComponent(new GamePlayerCreationPanel(window, numberOfPlayers)));
        }
        return actionListBox;
    }
}
