package CLI.panel.game;

import CLI.Theme;
import backend.Question;
import com.googlecode.lanterna.gui2.*;

public class CorrectAnswerPanel extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final Game game;
    final Question question;
    String answerOption;
    AnswerDialogPanel answerDialogPanel;
    Button continueButton;

    protected CorrectAnswerPanel(Game game, Question question, AnswerDialogPanel answerDialogPanel, String answerOption) {
        super(new LinearLayout(Direction.VERTICAL));
        this.game = game;
        this.question = question;
        this.answerDialogPanel = answerDialogPanel;
        this.answerOption = answerOption;

        setLayoutData(layoutData);
        setTheme(Theme.getTheme());

        addComponent(new EmptySpace());
        addComponent(new EmptySpace());
        addComponent(new Label(question.question));
        addComponent(new Label("The correct Answer is: " + question.answer).setLayoutData(layoutData));
        addComponent(new EmptySpace());
        continueButton = new Button("Continue", () -> handleContinue());
        continueButton.setLayoutData(layoutData);
        addComponent(continueButton);
        addComponent(new EmptySpace());
    }

    protected void handleContinue(){
        //game.removeComponent(answerDialogPanel);
        
        game.enableButtons(true);
        //game.validatePlayerAnswer(answerOption.equals(question.answer));
        //game.setCurrentTitleLabel(false);
        game.removeComponent(this);
        continueButton.setEnabled(false);
        continueButton.setVisible(false);
    }
}
