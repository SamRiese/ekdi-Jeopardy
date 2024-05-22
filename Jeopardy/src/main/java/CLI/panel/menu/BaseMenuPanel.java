package CLI.panel.menu;

import CLI.Theme;
import com.googlecode.lanterna.gui2.*;

/**
 * BaseMenuPanel is an abstract class that provides a template for creating menu panels.
 * It sets up a common layout, theme, and components such as a title label and an action list box.
 */
public abstract class BaseMenuPanel extends Panel {
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

    /**
     * Constructs a BaseMenuPanel.
     * Sets up the layout, theme, title, and an action list box.
     *
     * @param window the main application window
     */
    public BaseMenuPanel(CLI.Window window) {
        super(new LinearLayout(Direction.VERTICAL));
        this.window = window;

        setTheme(Theme.getTheme());
        addComponent(getTitleAsLabel());
        addComponent(new EmptySpace());
        addComponent(getActionListBox());
    }

    /**
     * Creates a Label component for the panel's title.
     * Subclasses should override this method to customize the Label.
     *
     * @return a Label with the ASCII art title
     */
    protected Label getTitleAsLabel() {
        return new Label(JEOPARDY).setLayoutData(layoutData);
    }

    /**
     * Creates an ActionListBox component for the panel.
     * Subclasses should override this method to customize the action list box.
     *
     * @return a new ActionListBox
     */
    protected ActionListBox getActionListBox() {
        return new ActionListBox();
    }
}
