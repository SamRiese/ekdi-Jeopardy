package CLI.panel.menu;

import CLI.Theme;
import CLI.Window;
import CLI.panel.game.Game;
import backend.Player;
import com.googlecode.lanterna.gui2.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GamePlayerCreationPanel is a panel for creating player profiles for the game.
 * It collects player names and initializes the game with the specified number of players.
 */
public class GamePlayerCreationPanel extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final Window window;
    final int numberOfPlayers;
    List<Player> players;

    /**
     * Constructs a GamePlayerCreationPanel with the specified window and number of players.
     * Sets up the theme and initiates the player creation interface.
     *
     * @param window the main application window
     * @param numberOfPlayers the number of players to be created
     */
    protected GamePlayerCreationPanel(Window window, int numberOfPlayers) {
        super(new LinearLayout(Direction.VERTICAL));
        this.window = window;
        this.numberOfPlayers = numberOfPlayers;

        this.players = new ArrayList<>(numberOfPlayers);
        setTheme(Theme.getTheme());
        getPlayerCreationInterface(numberOfPlayers);
    }

    /**
     * Sets up the player creation interface for collecting player names.
     * Recursively collects names until the specified number of players is reached.
     *
     * @param numberOfPlayers the number of players remaining to be created
     * @param currentPlayer the current player's index, defaults to 1 if not provided
     */
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
                    this.players.add(new Player(textBox.getText()));
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
            window.setComponent(new Game(window, players));
        }
    }

    /**
     * Creates an array of EmptySpace components for layout purposes.
     *
     * @param size the number of EmptySpace components to create
     * @return an array of EmptySpace components
     */
    private EmptySpace[] createEmptySpaces(final int size) {
        EmptySpace[] emptySpaces = new EmptySpace[size];

        for (int i = 0; i < emptySpaces.length; i++) {
            emptySpaces[i] = new EmptySpace();
        }
        return emptySpaces;
    }

    /**
     * Removes an array of EmptySpace components from the panel.
     *
     * @param emptySpaces the array of EmptySpace components to remove
     */
    private void removeEmptySpaces(EmptySpace[] emptySpaces) {
        Arrays.stream(emptySpaces).forEach(this::removeComponent);
    }
}
