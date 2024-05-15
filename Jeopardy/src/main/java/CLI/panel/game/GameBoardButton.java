package CLI.panel.game;

import backend.Question;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.LinearLayout;

public class GameBoardButton extends Button {
    Question question;
    Game game;

    protected GameBoardButton(String label, Game game, Question question) {
        super(label);
        this.game = game;
        this.question = question;

        setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Center));

        addListener(button -> {
            button.setVisible(false);
            button.setEnabled(false);
            game.enableButtons(false);
            game.setCurrentQuestionScore(Integer.parseInt(label));
            game.setCurrentTitleLabel(true);
            game.addComponent(new ShowQuestionDialogPanel(game, question));
        });
    }
}
