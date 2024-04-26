package cli.menu;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;

public class MainMenu {

    public static void run() {
        int playerInput;
        boolean input = false;

        do {
            printMainMenu();

            if (input) {
                System.out.print(ansi()
                        .a("Invalid Option! Try Again:")
                        .newline()
                );
            }

            playerInput = Menu.getUserInput();
            input = !(playerInput >= 1 && playerInput <= 3);

        } while (input);

        switch (playerInput) {
            case 1 -> GameMenu.run();
            case 2 -> OptionMenu.run();
            case 3 -> System.exit(0);
        }
    }

    private static void printMainMenu() {
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
    }

}