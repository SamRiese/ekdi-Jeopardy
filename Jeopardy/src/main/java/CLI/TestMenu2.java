package CLI;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;


public class TestMenu2 {
    static int terminalCenterWidth;
    static int terminalCenterHeight;
    static int currentComponents = 0;

    public static void main(String[] args) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        terminalCenterWidth = terminal.getTerminalSize().getColumns() / 2;
        terminalCenterHeight = terminal.getTerminalSize().getRows() / 4;

        BasicWindow basicWindow = new BasicWindow();
        SimpleTheme simpleTheme = SimpleTheme.makeTheme(
                false,
                TextColor.ANSI.WHITE,
                TextColor.ANSI.BLACK,
                TextColor.ANSI.WHITE,
                TextColor.ANSI.BLACK,
                TextColor.ANSI.BLACK,
                TextColor.ANSI.WHITE,
                TextColor.ANSI.BLACK
        );
        Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
        panel.setTheme(simpleTheme);
        panel.addComponent(getLabel(terminal));
        panel.addComponent(new Label(""));
        panel.addComponent(createActionListBox(terminal));
        System.out.println(panel.getChildCount());

        basicWindow.setTheme(simpleTheme);
        basicWindow.setComponent(panel);

        MultiWindowTextGUI multiWindowTextGUI = new MultiWindowTextGUI(
                screen
        );
        multiWindowTextGUI.setTheme(simpleTheme);

        System.out.println(
                terminal.getTerminalSize().getRows()
                        + "  terminal  " +
                        terminal.getTerminalSize().getColumns()
        );

        System.out.println(
                screen.getTerminalSize().getRows()
                        + "  screen  " +
                        screen.getTerminalSize().getColumns()
        );

        System.out.println(
                panel.getSize().getRows()
                        + "  panel  " +
                        panel.getSize().getColumns()
        );

        multiWindowTextGUI.addWindowAndWait(basicWindow);
    }

    private static ActionListBox createActionListBox(Terminal terminal) throws IOException {
        ActionListBox actionListBox = new ActionListBox();

        actionListBox.addItem("Start", () -> System.out.println("Start")
        );

        actionListBox.addItem("Stop", new Runnable() {
            @Override
            public void run() {
                System.out.println("Stop");
            }
        });
//
//        actionListBox.setSize(new TerminalSize(5, 2));
//        actionListBox.setPosition(new TerminalPosition(
//                (terminalCenterWidth - (actionListBox.getSize().getColumns() / 2)),
//                terminalCenterHeight + currentComponents + 1
//        ));
        currentComponents += 1;
        return actionListBox;
    }

    private static Label getLabel(Terminal terminal) throws IOException {
        String labelString = " Das hier ist ein Test";
        Label label = new Label(labelString);
//        label.setSize(new TerminalSize(labelString.length(), 1));
//        label.setPosition(new TerminalPosition((terminalCenterWidth - (label.getSize().getColumns() / 2)),
//                terminalCenterHeight));
//        currentComponents += 1;
        return label;
    }
}
