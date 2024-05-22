package CLI.panel.game;

import CLI.Theme;
import backend.Question;
import com.googlecode.lanterna.gui2.*;

/**
 * The AnswerDialogPanel class provides a panel to display a question and its possible answers.
 */
public class AnswerDialogPanel extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final Game game;
    final Question question;

    /**
     * Constructs an AnswerDialogPanel.
     *
     * @param game     the Game instance
     * @param question the current question
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
     * Creates and returns an ActionListBox with answer options A), B), C), D).
     *
     * @return the ActionListBox with answer options
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
                game.validatePlayerAnswer(answerOption.equals(question.answer));
                game.setCurrentTitleLabel(false);
            });
            index++;
        }
        return answerActionListBox;
    }
}
