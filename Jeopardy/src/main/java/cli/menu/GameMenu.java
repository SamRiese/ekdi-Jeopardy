package cli.menu;

import cli.Main;

import static org.fusesource.jansi.Ansi.ansi;

public class GameMenu {

    public static void run() {
        int playerInput;
        boolean invalidInput = false;

        do {
            printGameMenu(invalidInput);
            playerInput = Menu.getUserInput();
            invalidInput = !(playerInput >= 1 && playerInput <= 4);
        } while (invalidInput);

        switch (playerInput) {
            case 1 -> GameMenu.run();
            case 2 -> GameMenu.run();
            case 3 -> GameMenu.run();
            case 4 -> MainMenu.run();
        }
    }

    private static void printGameMenu(boolean invalidInput) {
        System.out.print(ansi()
                .cursor(0, 0)
                .eraseScreen()
                .a("Choose Game Mode:")
                .newline()
                .newline()
                .a("[1] Start New Game")
                .newline()
                .a("[2] Start Singleplayer")
                .newline()
                .a("[3] Return to Saved Game")
                .newline()
                .a("[4] Exit")
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
