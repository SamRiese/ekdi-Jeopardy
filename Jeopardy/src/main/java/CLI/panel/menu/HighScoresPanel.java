package CLI.panel.menu;


import CLI.Theme;
import CLI.Window;
import backend.Score;
import backend.ScoreHandler;
import com.googlecode.lanterna.gui2.*;

import java.util.List;

public class HighScoresPanel extends Panel {

    private static final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);

    protected HighScoresPanel(Window window) {
        super(new LinearLayout(Direction.VERTICAL));

        setTheme(Theme.getTheme());

        addComponent(new Label("High Scores").setLayoutData(layoutData));
        addComponent(new EmptySpace());
        addComponent(createScoreBoard());
        addComponent(new EmptySpace());
        addComponent(new Button("Exit", () -> window.setComponent(new MainMenuPanel(window)))
                .setLayoutData(layoutData));
    }

    private Panel createScoreBoard() {
        Panel panel = new Panel(new GridLayout(1)).setLayoutData(layoutData);
        int counter = 1;

        ScoreHandler scoreHandler = new ScoreHandler();
        scoreHandler.loadScores();


        for (Score score : scoreHandler.getScores()) {
            if ( counter <= 10) {
                panel.addComponent(new Label(counter + ") " + score.name + ": " + score.score).setLayoutData(layoutData));
                counter++;
            }
        }
        return panel;
    }
}






























