package CLI.panel.game;

import CLI.Theme;
import backend.Player;
import backend.Question;
import com.googlecode.lanterna.gui2.*;

/**
 * ShowQuestionDialogPanel is a panel that displays the selected question and offers the option of choosing the player
 * who activated the buzzer first.
 * If there is only one player, it directly moves to the answer dialog.
 */
public class ShowQuestionDialogPanel extends Panel {
    static final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    Game game;
    Question question;

    /**
     * Constructs a ShowQuestionDialogPanel with the specified game and question.
     * Sets up the layout, theme, and initializes the components for displaying the question and selecting the player.
     *
     * @param game     the current game instance
     * @param question the question to be displayed
     */
    protected ShowQuestionDialogPanel(Game game, Question question) {
        super(new LinearLayout(Direction.VERTICAL));
        this.game = game;
        this.question = question;

        setLayoutData(layoutData);
        setTheme(Theme.getTheme());

        if (game.getPlayerList().size() == 1) {
            game.setCurrentPlayer(game.getPlayerList().getFirst());
            game.removeComponent(this);
            game.addComponent(new AnswerDialogPanel(game, question));
        } else {
            addComponent(new EmptySpace());
            addComponent(new EmptySpace());
            addComponent(new Label(question.question).setLayoutData(layoutData));
            addComponent(new EmptySpace());
            addComponent(answerActionListBox());
            addComponent(new EmptySpace());
            addComponent(choosePlayer());
        }
    }

    /**
     * Creates an ActionListBox to display the answer options for the question.
     * The options are displayed but not enabled for selection in this panel.
     *
     * @return the ActionListBox with answer options
     */
    private ActionListBox answerActionListBox() {
        String[] abcd = {"A) ", "B) ", "C) ", "D) "};
        ActionListBox answerActionListBox = new ActionListBox();

        int index = 0;
        for (String answer : question.options) {
            answerActionListBox.addItem(abcd[index] + answer, () -> {});
            index++;
        }
        answerActionListBox.setLayoutData(layoutData);
        answerActionListBox.setEnabled(false);

        return answerActionListBox;
    }

    /**
     * Creates a panel with buttons for each player to select who will answer the question.
     * When a player is selected, it moves to the answer dialog for that player.
     *
     * @return the panel with player selection buttons
     */
    private Panel choosePlayer() {
        Panel choosePlayerPanel = new Panel(new LinearLayout(Direction.HORIZONTAL)).setLayoutData(layoutData);

        for (Player player : game.getPlayerList()) {
            Button button = new Button(player.getName());

            button.addListener(button1 -> {
                button.setEnabled(false);
                game.removeComponent(this);
                game.setCurrentPlayer(player);
                game.addComponent(new AnswerDialogPanel(game, question));
            });

            choosePlayerPanel.addComponent(button);
        }

        return choosePlayerPanel;
    }
}
