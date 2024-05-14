package CLI.panel.game;

import CLI.Theme;
import com.googlecode.lanterna.gui2.*;

public class CurrentPlayerSelectorPanel extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final Game game;

    protected CurrentPlayerSelectorPanel(Game game) {
        super(new LinearLayout(Direction.VERTICAL));
        this.game = game;
        game.enableButtons(false);

        if (game.getNumberOfPlayers() == 1) {
            game.removeComponent(this);
//            game.addComponent(new AnswerDialogPanel());
        } else {
            setLayoutData(layoutData);
            setTheme(Theme.getTheme());

            addComponent(new EmptySpace());
            addComponent(new EmptySpace());
            addComponent(new Label("Select Player:").setLayoutData(layoutData));
            addComponent(new EmptySpace());
            addComponent(playerSelectorPanel());
        }
    }
    
    private Panel playerSelectorPanel() {
        Panel playerSelectorPanel = new Panel(new GridLayout(2)).setLayoutData(layoutData);

        for (int i = 0; i < game.getNumberOfPlayers(); i++) {
            Button button = new Button("Player");

            button.addListener(button1 -> {
                button.setEnabled(false);
                game.removeComponent(this);
                game.selectCurrentPlayer();
//                game.addComponent(new AnswerDialogPanel(game));
            });

            playerSelectorPanel.addComponent(button);
        }

        return playerSelectorPanel;
    }
}
