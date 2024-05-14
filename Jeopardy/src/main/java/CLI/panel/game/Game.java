package CLI.panel.game;

import CLI.Theme;
import CLI.Window;
import com.googlecode.lanterna.gui2.*;

public class Game extends Panel {
    protected static final String JEOPARDY =
            "       _                                _       \n" +
                    "      | |                              | |      \n" +
                    "      | | ___  ___  _ __   __ _ _ __ __| |_   _ \n" +
                    "  _   | |/ _ \\/ _ \\| '_ \\ / _` | '__/ _` | | | |\n" +
                    " | |__| |  __/ (_) | |_) | (_| | | | (_| | |_| |\n" +
                    "  \\____/ \\___|\\___/| .__/ \\__,_|_|  \\__,_|\\__, |\n" +
                    "                   | |                     __/ |\n" +
                    "                   |_|                    |___/";
    protected static final int gameBoardHeight = 4;
    protected static final int gameBoardWidth = 5;
    protected static final int gameBoardSize = gameBoardHeight * gameBoardWidth;

    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    CLI.Window window;
    Scoreboard scoreboard;
    int availableQuestions = gameBoardSize;
    final int numberOfPlayers;
    int currentPlayer = 0;
    Button currentGameButton;

    public Game(Window window, int numberOfPlayers) {
        super(new LinearLayout(Direction.VERTICAL));
        this.window = window;
        this.numberOfPlayers = numberOfPlayers;
        this.scoreboard = new Scoreboard(this);

        setTheme(Theme.getTheme());

        addComponent(new GameBoardMenuBarPanel(this));
        addComponent(new EmptySpace());
        addComponent(getTitleAsLabel());
        addComponent(new EmptySpace());
        addComponent(scoreboard);
        addComponent(new EmptySpace());
        addComponent(getGameBoard());
    }

    protected Label getTitleAsLabel() {
//        return new Label(JEOPARDY).setLayoutData(layoutData);
        return new Label("JEOPARDY").setLayoutData(layoutData);
    }

    protected Panel getGameBoard() {
        Panel gameboard = new Panel(new GridLayout(gameBoardWidth)).setLayoutData(layoutData);

        //category name
        for (int i = 0; i < gameBoardWidth; i++) {
            gameboard.addComponent(new Label("Placeholder").setLayoutData(layoutData));
        }

        //questions
        for (int i = 1; i <= gameBoardHeight; i++) {
            int currentLabel = i * 100;
            for (int j = 0; j < gameBoardWidth; j++) {
                gameboard.addComponent(createButton(String.valueOf(currentLabel)));
            }

        }

        return gameboard;
    }

    protected Button createButton(String label) {
        Button button = new Button(label);

        button.addListener(button1 -> {
            button.setVisible(false);
            button.setEnabled(false);
            currentGameButton = button;
            this.addComponent(new CurrentPlayerSelectorPanel(this));
        });

        return button;
    }

    protected void enableButtons(boolean enable) {
        for (Component component : getChildrenList()) {
            if (component instanceof Panel) {
                for (Component child : ((Panel) component).getChildrenList()) {
                    if (child instanceof Button) {
                        if (child.isVisible()) {
                            ((Button) child).setEnabled(enable);
                        }
                    }
                }
            }
        }
    }

    protected void selectCurrentPlayer() {
//        System.out.println("Select current player");
    }

    protected void validatePlayerAnswer() {
        scoreboard.updatePlayerScore(Integer.parseInt(currentGameButton.getLabel()));
    //        System.out.println("validatePlayerAnswer");
    }

    protected Panel getScoreboard() {
        Panel scoreboard = new Panel(new GridLayout(numberOfPlayers)).setLayoutData(layoutData);

        for (int i = 1; i <= numberOfPlayers; i++) {
            scoreboard.addComponent(new Label("Player " + String.valueOf(i) + ":"));
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            scoreboard.addComponent(new Label(String.valueOf(i)));
        }

        return scoreboard;
    }
}
