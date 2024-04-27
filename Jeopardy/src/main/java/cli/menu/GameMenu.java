package cli.menu;

import cli.CLI;
import cli.game.Game;

import static org.fusesource.jansi.Ansi.ansi;

public class GameMenu {

    private static final String GAME_MENU = String.valueOf(
                    ansi()
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

    public static void run() {
        int playerInput;
        boolean invalidInput = false;

        do {
            CLI.printMenu(invalidInput, GAME_MENU);
            playerInput = CLI.getUserInput();
            invalidInput = !(playerInput >= 1 && playerInput <= 4);
        } while (invalidInput);

        switch (playerInput) {
            case 1 -> Game.playerCreation();
            case 2 -> GameMenu.run();
            case 3 -> GameMenu.run();
            case 4 -> MainMenu.run();
        }
    }
}
