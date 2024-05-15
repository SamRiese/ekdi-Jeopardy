package CLI.panel.game;

import CLI.Theme;
import backend.Question;
import com.googlecode.lanterna.gui2.*;

public class AnswerDialogPanel extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final Game game;
    final Question question;

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

    protected ActionListBox answerDialogPanel() {
        String[] abcd = {"A) ", "B) ", "C) ", "D) "};
        ActionListBox answerActionListBox = new ActionListBox();
        answerActionListBox.setLayoutData(layoutData);

        int index = 0;
        for (String answerOption : question.options) {
            answerActionListBox.addItem(abcd[index] + answerOption, () -> {
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
