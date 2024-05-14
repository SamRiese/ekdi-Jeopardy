package CLI;

import CLI.panel.menu.MainMenuPanel;
import com.googlecode.lanterna.gui2.BasicWindow;

import java.util.List;

public class Window extends BasicWindow {
    public Window() {
        super();
        setHints(List.of(Window.Hint.CENTERED, Window.Hint.NO_DECORATIONS));
        setTheme(Theme.getTheme());
        setComponent(new MainMenuPanel(this));
    }
}
