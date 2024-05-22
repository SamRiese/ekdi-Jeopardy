package CLI.panel.menu;

import CLI.Window;
import CLI.panel.game.Game;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Label;


public class GameSetupPanel extends BaseMenuPanel {
    public GameSetupPanel(Window window) {
        super(window);
        addComponent(new EmptySpace());
        addComponent(
                new Button("Exit", () -> window.setComponent(new MainMenuPanel(window))).setLayoutData(layoutData)
        );
    }

    @Override
    protected Label getTitleAsLabel() {
        return new Label("Choose Number of Players:").setLayoutData(layoutData);
    }

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
