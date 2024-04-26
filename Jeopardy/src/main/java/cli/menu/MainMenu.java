package cli.menu;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;

public class MainMenu {

    public static void run() {
        int playerInput;
        boolean invalidInput = false;

        do {
            printMainMenu(invalidInput);
            playerInput = Menu.getUserInput();
            invalidInput = !(playerInput >= 1 && playerInput <= 3);
        } while (invalidInput);

        switch (playerInput) {
            case 1 -> GameMenu.run();
            case 2 -> OptionMenu.run();
            case 3 -> System.exit(0);
        }
    }

    private static void printMainMenu(boolean invalidInput) {
        AnsiConsole.systemInstall();
        System.out.print(ansi()
                .cursor(0,0)
                .eraseScreen()
                .a("Welcome to Jeopardy!")
                .newline()
                .newline()
                .a("Main Menu:")
                .newline()
                .newline()
                .a("[1] Game Menu")
                .newline()
                .a("[2] Option Menu")
                .newline()
                .a("[3] Exit")
                .newline()
                .newline()
        );

        if (invalidInput) {
            System.out.print(ansi()
                    .a("Invalid Option! Try Again:")
                    .newline()
            );
        }
    }

}
