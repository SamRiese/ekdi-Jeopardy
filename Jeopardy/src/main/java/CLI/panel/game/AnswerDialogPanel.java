package CLI.panel.game;

import CLI.Theme;
import backend.Question;
import com.googlecode.lanterna.gui2.*;

/**
 * AnswerDialogPanel is a panel that displays a question and its possible answers.
 * Players can select an answer, and the game will validate the response.
 */
public class AnswerDialogPanel extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final Game game;
    final Question question;

    /**
     * Constructs an AnswerDialogPanel with the specified game and question.
     * Sets up the layout, theme, and components to display the question and answers.
     *
     * @param game the current game instance
     * @param question the question to be displayed
     */
    protected AnswerDialogPanel(Game game, Question question) {
        super(new LinearLayout(Direction.VERTICAL));
        this.game = game;
        this.question = question;

        setLayoutData(layoutData);
        setTheme(Theme.getTheme());

        addComponent(new EmptySpace());
        addComponent(new EmptySpace());
        addComponent(new Label(question.question).setLayoutData(layoutData));
        addComponent(new EmptySpace());
        addComponent(answerDialogPanel());
    }

    /**
     * Creates an ActionListBox containing the answer options.
     * When an option is selected, it validates the answer and updates the game state.
     *
     * @return an ActionListBox with answer options
     */
    protected ActionListBox answerDialogPanel() {
        String[] abcd = {"A) ", "B) ", "C) ", "D) "};
        ActionListBox answerActionListBox = new ActionListBox();
        answerActionListBox.setLayoutData(layoutData);

        int index = 0;
        for (String answerOption : question.options) {
            answerActionListBox.addItem(abcd[index] + answerOption, () -> {
                answerActionListBox.setEnabled(false);
                answerActionListBox.setVisible(false);
                game.addComponent(new CorrectAnswerPanel(game, question));
                game.removeComponent(this);
                game.enableButtons(true);
                game.validatePlayerAnswer(answerOption.equals(question.answer));
                game.setCurrentTitleLabel(false);
            });
            index++;
        }
        return answerActionListBox;
    }
}
