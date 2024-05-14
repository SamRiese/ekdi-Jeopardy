package CLI.panel.game;

import CLI.Theme;
import com.googlecode.lanterna.gui2.*;

public class AnswerDialogPanel extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final Game game;

    protected AnswerDialogPanel(Game game) {
        super();
        this.game = game;

        setLayoutData(layoutData);
        setTheme(Theme.getTheme());

        addComponent(new EmptySpace());
        addComponent(new EmptySpace());
        addComponent(getQuestionAsLabel());
        addComponent(new EmptySpace());
        addComponent(answerDialogPanel());
    }

    protected Label getQuestionAsLabel() {
        String question = "Das hier ist eine lange Frage";
        return new Label(question).setLayoutData(layoutData);
    }

    protected Panel answerDialogPanel() {
        Panel answerDialogPanel = new Panel(new GridLayout(2)).setLayoutData(layoutData);

        for (int i = 0; i < 4; i++) {
            Button button = new Button("Answer");

            button.addListener(button1 -> {
                button.setEnabled(false);
                game.removeComponent(this);
                game.validatePlayerAnswer();
                game.enableButtons(true);
            });

            answerDialogPanel.addComponent(button);
        }

        return answerDialogPanel;
    }
}
