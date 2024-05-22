package CLI.panel.menu;

import CLI.Window;
import com.googlecode.lanterna.gui2.*;

/**
 * MainMenuPanel is the main menu panel of the CLI application.
 * It extends the BaseMenuPanel and customizes the action list box with options for navigating the application.
 */
public class MainMenuPanel extends BaseMenuPanel {

    /**
     * Constructs a MainMenuPanel.
     *
     * @param window the main application window
     */
    public MainMenuPanel(Window window) {
        super(window);
    }


    /**
     * Creates an ActionListBox component for the panel.
     * Overrides the method from BaseMenuPanel to provide options for the main menu.
     *
     * @return a new ActionListBox with main menu options
     */
    @Override
    protected ActionListBox getActionListBox() {
        ActionListBox actionListBox = new ActionListBox().setLayoutData(layoutData);

        actionListBox.addItem("Game Menu", () -> window.setComponent(new GameSetupPanel(window)));
        actionListBox.addItem("High Scores", () -> window.setComponent(new HighScoresPanel(window)));
        actionListBox.addItem("Exit", () -> window.close());

        return actionListBox;
    }
}
