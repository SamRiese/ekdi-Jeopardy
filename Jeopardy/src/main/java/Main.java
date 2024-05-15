import CLI.Theme;
import CLI.Window;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        Window window = new Window();

        MultiWindowTextGUI textGUI = new MultiWindowTextGUI(screen);
        textGUI.setTheme(Theme.getTheme());
        textGUI.addWindowAndWait(window);

        if (textGUI.getActiveWindow() == null) {
            screen.close();
        }
    }
}
