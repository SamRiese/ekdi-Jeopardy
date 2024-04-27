package cli.menu;

import cli.CLI;

import static org.fusesource.jansi.Ansi.*;

public class MainMenu {

    private static final String Main_Menu = String.valueOf(
                    ansi()
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

    public static void run() {
        int playerInput;
        boolean invalidInput = false;

        do {
            CLI.printMenu(invalidInput, Main_Menu);
            playerInput = CLI.getUserInput();
            invalidInput = !(playerInput >= 1 && playerInput <= 3);
        } while (invalidInput);

        switch (playerInput) {
            case 1 -> GameMenu.run();
            case 2 -> OptionMenu.run();
            case 3 -> System.exit(0);
        }
    }

}
