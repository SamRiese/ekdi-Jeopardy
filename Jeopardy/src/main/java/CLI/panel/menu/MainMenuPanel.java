package CLI.panel.menu;

import CLI.Window;
import com.googlecode.lanterna.gui2.*;

public class MainMenuPanel extends BaseMenuPanel {

    public MainMenuPanel(Window window) {
        super(window);
    }

    @Override
    protected ActionListBox getActionListBox() {
        ActionListBox actionListBox = new ActionListBox().setLayoutData(layoutData);

        actionListBox.addItem("Game Menu", () -> window.setComponent(new GameSetupPanel(window)));
        actionListBox.addItem("High Scores", () -> window.setComponent(new HighScoresPanel(window)));
        actionListBox.addItem("Exit", () -> window.close());

        return actionListBox;
    }
}
