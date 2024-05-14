package CLI.panel.menu;

import CLI.Window;
import CLI.panel.game.Game;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Label;


public class GameSetupPanel extends MainMenuPanel {
    public GameSetupPanel(Window window) {
        super(window);
        addComponent(new EmptySpace());
        addComponent(
                new Button("Exit", () -> window.setComponent(new GameMenuPanel(window))).setLayoutData(layoutData)
        );
    }

    @Override
    protected Label getTitleAsLabel() {
        return new Label("Choose Number of Players:").setLayoutData(layoutData);
    }

    @Override
    protected ActionListBox getActionListBox() {
        ActionListBox actionListBox = new ActionListBox().setLayoutData(layoutData);

        for (int i = 1; i <= 4; i++) {
            int numberOfPlayers = i;
            actionListBox.addItem(String.valueOf(i), () -> window.setComponent(new GamePlayerCreationPanel(window, numberOfPlayers)));
        }
//        new Game(window, numberOfPlayers)

        return actionListBox;
    }
}
