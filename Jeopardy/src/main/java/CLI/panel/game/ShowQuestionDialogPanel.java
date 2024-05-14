package CLI.panel.game;

import CLI.Theme;
import backend.Player;
import backend.Question;
import com.googlecode.lanterna.gui2.*;

public class ShowQuestionDialogPanel extends Panel {
    static final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    Game game;
    Question question;

    protected ShowQuestionDialogPanel(Game game, Question question) {
        super(new LinearLayout(Direction.VERTICAL));
        this.game = game;
        this.question = question;

        setLayoutData(layoutData);
        setTheme(Theme.getTheme());

        addComponent(new EmptySpace());
        addComponent(new EmptySpace());
        addComponent(new Label(question.question).setLayoutData(layoutData));
        addComponent(new EmptySpace());
        addComponent(answerActionListBox());
        addComponent(new EmptySpace());
        addComponent(choosePlayer());

    }

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
