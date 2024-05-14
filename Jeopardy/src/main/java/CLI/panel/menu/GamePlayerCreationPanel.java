package CLI.panel.menu;

import CLI.Theme;
import CLI.Window;
import CLI.panel.game.Game;
import com.googlecode.lanterna.gui2.*;

import java.util.Arrays;


public class GamePlayerCreationPanel extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final Window window;
    final int numberOfPlayers;

    protected GamePlayerCreationPanel(Window window, int numberOfPlayers) {
        super(new LinearLayout(Direction.VERTICAL));
        this.window = window;
        this.numberOfPlayers = numberOfPlayers;

        setTheme(Theme.getTheme());
        getPlayerCreationInterface(numberOfPlayers);
    }

    protected void getPlayerCreationInterface(int numberOfPlayers, int... currentPlayer) {
        if (numberOfPlayers != 0) {
            int player = currentPlayer.length == 0 ? 1 : currentPlayer[0];
            EmptySpace[] emptySpaces = createEmptySpaces(3);
            Button exitButton = new Button("Exit", () -> window.setComponent(new GameSetupPanel(window)))
                    .setLayoutData(layoutData);

            Label label = new Label("Please Enter your Name Player " + player + ":")
                    .setLayoutData(layoutData);

            TextBox textBox = new TextBox();
            textBox.setLayoutData(layoutData);

            Button saveButton = new Button("Save", () -> {});
            saveButton.setLayoutData(layoutData);
            saveButton.addListener(button1 -> {
                if (!textBox.getText().isEmpty()) {
                    removeEmptySpaces(emptySpaces);
                    removeComponent(label);
                    removeComponent(textBox);
                    removeComponent(saveButton);
                    removeComponent(exitButton);
                    getPlayerCreationInterface(numberOfPlayers - 1, player + 1);
                }
            });

            addComponent(label);
            addComponent(emptySpaces[0]);
            addComponent(textBox);
            addComponent(emptySpaces[1]);
            addComponent(saveButton);
            addComponent(emptySpaces[2]);
            addComponent(exitButton);
        } else {
            window.setComponent(new Game(window, this.numberOfPlayers));
        }
    }

    private EmptySpace[] createEmptySpaces(final int size) {
        EmptySpace[] emptySpaces = new EmptySpace[size];

        for (int i = 0; i < emptySpaces.length; i++) {
            emptySpaces[i] = new EmptySpace();
        }
        return emptySpaces;
    }

    private void removeEmptySpaces(EmptySpace[] emptySpaces) {
        Arrays.stream(emptySpaces).forEach(this::removeComponent);
    }
}
