package cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI {

    private void renderGameBoard() {

    }

    private void renderGameMode() {

    }

    public static GameState renderMainMenu() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();

        switch (input) {
            case 1 -> {
                return GameState.GAME_MENU;
            }
            case 2 -> {
                return GameState.OPTION_MENU;
            }
            case 3 -> {
                return GameState.EXIT;
            }
            default -> {
                return GameState.MAIN_MENU;
            }
        }
    }
}
