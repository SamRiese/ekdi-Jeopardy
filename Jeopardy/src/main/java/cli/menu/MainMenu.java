package cli.menu;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;

public class MainMenu {

    private enum MenuOption {
        MAIN_MENU,
        GAME_MENU,
        OPTION_MENU,
        EXIT
    }

    public static void run() {
        MenuOption playerInput;

        do {
            printMainMenu();
            playerInput = validateUserInput(Menu.getUserInput());
        } while (playerInput == MenuOption.MAIN_MENU);

        switch (playerInput) {
            case MenuOption.GAME_MENU -> OptionMenu.run();
            case MenuOption.OPTION_MENU -> OptionMenu.run();
            case MenuOption.EXIT -> System.exit(1);
        }
    }

    private static void printMainMenu() {
        AnsiConsole.systemInstall();
        System.out.print(ansi()
                .cursor(0,0)
                .eraseScreen()
                .a("[1] Game Menu")
                .newline()
                .a("[2] Option Menu")
                .newline()
                .a("[3] Exit")
                .newline()
        );
    }

    private static MenuOption validateUserInput(int input) {
        switch (input) {
            case 1 -> {
                return MenuOption.GAME_MENU;
            }
            case 2 -> {
                return MenuOption.OPTION_MENU;
            }
            case 3 -> {
                return MenuOption.EXIT;
            }
            default -> {
                return MenuOption.MAIN_MENU;
            }
        }
    }

}
