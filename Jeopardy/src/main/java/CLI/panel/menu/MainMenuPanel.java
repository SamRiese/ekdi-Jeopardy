package CLI.panel.menu;

import CLI.Theme;
import CLI.Window;
import com.googlecode.lanterna.gui2.*;

public class MainMenuPanel extends Panel {
    private static final String JEOPARDY =
            "       _                                _       \n" +
                    "      | |                              | |      \n" +
                    "      | | ___  ___  _ __   __ _ _ __ __| |_   _ \n" +
                    "  _   | |/ _ \\/ _ \\| '_ \\ / _` | '__/ _` | | | |\n" +
                    " | |__| |  __/ (_) | |_) | (_| | | | (_| | |_| |\n" +
                    "  \\____/ \\___|\\___/| .__/ \\__,_|_|  \\__,_|\\__, |\n" +
                    "                   | |                     __/ |\n" +
                    "                   |_|                    |___/";

    final LayoutData layoutData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center);
    CLI.Window window;

    public MainMenuPanel(Window window) {
        super(new LinearLayout(Direction.VERTICAL));
        this.window = window;

        setTheme(Theme.getTheme());
        addComponent(getTitleAsLabel());
        addComponent(new EmptySpace());
        addComponent(getActionListBox());
    }

    protected Label getTitleAsLabel() {
        return new Label(JEOPARDY).setLayoutData(layoutData);
    }

    protected ActionListBox getActionListBox() {
        ActionListBox actionListBox = new ActionListBox().setLayoutData(layoutData);

        actionListBox.addItem("Game Menu", () -> window.setComponent(new GameMenuPanel(window)));
        actionListBox.addItem("High Scores", () -> window.setComponent(new HighScoresPanel(window)));
        actionListBox.addItem("Exit", () -> window.close());

        return actionListBox;
    }
}
