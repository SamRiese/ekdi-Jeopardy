package CLI;

import CLI.panel.menu.MainMenuPanel;
import com.googlecode.lanterna.gui2.BasicWindow;

import java.util.List;

/**
 * Window is a custom BasicWindow subclass used for displaying the application TUI.
 * It sets up the window with custom hints, theme, and initial Panel.
 */
public class Window extends BasicWindow {

    /**
     * Constructs a new Window instance.
     * Sets up the window with centered positioning and no decorations.
     * Sets the custom theme and sets the initial Panel.
     */
    public Window() {
        super();
        setHints(List.of(Window.Hint.CENTERED, Window.Hint.NO_DECORATIONS));
        setTheme(Theme.getTheme());
        setComponent(new MainMenuPanel(this));
    }
}
