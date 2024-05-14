package CLI.panel.menu;

import CLI.Window;
import com.googlecode.lanterna.gui2.*;

public class GameMenuPanel extends MainMenuPanel {

    public GameMenuPanel(Window window) {
        super(window);
    }

    @Override
    protected Label getTitleAsLabel() {
        return new Label("Choose Game Mode:").setLayoutData(layoutData);
    }

    @Override
    protected ActionListBox getActionListBox() {
        ActionListBox actionListBox = new ActionListBox().setLayoutData(layoutData);

        actionListBox.addItem("Start New Game", () -> window.setComponent(new GameSetupPanel(window)));
        actionListBox.addItem("Return to Saved Game", () -> window.close());
        actionListBox.addItem("Exit", () -> window.setComponent(new MainMenuPanel(window)));

        return actionListBox;
    }
}
