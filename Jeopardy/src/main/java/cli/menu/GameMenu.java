package cli.menu;

import cli.Main;

import static org.fusesource.jansi.Ansi.ansi;

public class GameMenu {

    public static void run() {
        int playerInput;
        boolean input = false;

        do {
            printGameMenu();

            if (input) {
                System.out.print(ansi()
                        .a("Invalid Option! Try Again:")
                        .newline()
                );
            }

            playerInput = Menu.getUserInput();
            input = !(playerInput >= 1 && playerInput <= 4);
            
        } while (input);

        switch (playerInput) {
            case 1 -> GameMenu.run();
            case 2 -> GameMenu.run();
            case 3 -> GameMenu.run();
            case 4 -> MainMenu.run();
        }
    }

    private static void printGameMenu() {
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
    }
}
