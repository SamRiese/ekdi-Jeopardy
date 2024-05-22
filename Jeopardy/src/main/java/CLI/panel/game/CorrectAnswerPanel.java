package CLI.panel.game;

import CLI.Theme;
import backend.Question;
import com.googlecode.lanterna.gui2.*;

/**
 * The CorrectAnswerPanel class represents a panel displayed after a player answered a question.
 * It conatins the correct answer and a continue button to proceed in the game.
 */
public class CorrectAnswerPanel extends Panel {
    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    final Game game;
    final Question question;
    Button continueButton;

    /**
     * Constructs a CorrectAnswerPanel.
     *
     * @param game                the Game instance
     * @param question            the last question asked / current question
     */
    protected CorrectAnswerPanel(Game game, Question question) {
        super(new LinearLayout(Direction.VERTICAL));
        this.game = game;
        this.question = question;

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
    
    /**
     * Actions executed when the continue button is pressed.
     */
    protected void handleContinue(){
        game.enableButtons(true);
        game.removeComponent(this);
        continueButton.setEnabled(false);
        continueButton.setVisible(false);
    }
}
