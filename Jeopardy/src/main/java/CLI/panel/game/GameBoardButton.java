package CLI.panel.game;

import backend.Question;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.LinearLayout;

/**
 * GameBoardButton is a custom button used in the game board to represent a question.
 * When clicked, it hides itself, disables all buttons, and shows the question dialog.
 */
public class GameBoardButton extends Button {
    Question question;
    Game game;

    /**
     * Constructs a GameBoardButton with the specified label, game, and question.
     * Sets up the layout and click listener.
     *
     * @param label the label to display on the button
     * @param game the current game instance
     * @param question the question associated with this button
     */
    protected GameBoardButton(String label, Game game, Question question) {
        super(label);
        this.game = game;
        this.question = question;

        setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));

        addListener(button -> {
            game.decrementRoundsLeft();
            button.setVisible(false);
            button.setEnabled(false);
            game.enableButtons(false);
            game.setCurrentQuestionScore(Integer.parseInt(label));
            game.setCurrentTitleLabel(true);
            game.addComponent(new ShowQuestionDialogPanel(game, question));
        });
    }
}
