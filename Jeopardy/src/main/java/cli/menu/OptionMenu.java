package cli.menu;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.*;

public class OptionMenu {

    private enum MenuOption {
        MAIN_MENU,
        GAME_MENU,
        OPTION_MENU,
        EXIT
    }

    public static void run() {
        MenuOption playerInput;

        do {
            printOptionMenu();
            playerInput = validateUserInput(Menu.getUserInput());
        } while (playerInput == MenuOption.OPTION_MENU);

        switch (playerInput) {
            case MenuOption.GAME_MENU -> MainMenu.run();
            case MenuOption.EXIT -> System.exit(1);
        }
    }

    private static void printOptionMenu() {
        AnsiConsole.systemInstall();
        for (int i = 0; i < 5; i++) {
            System.out.print(ansi().cursor(0,0).eraseScreen().render(String.valueOf(i)).cursorLeft(String.valueOf(i).length()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static MenuOption validateUserInput(int input) {
        switch (input) {
            case 1 -> {
                return MenuOption.EXIT;
            }
            case 2 -> {
                return MenuOption.GAME_MENU;
            }
            case 3 -> {
                return MenuOption.MAIN_MENU;
            }
            default -> {
                return MenuOption.OPTION_MENU;
            }
        }
    }

}
