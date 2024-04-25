package cli;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
import org.fusesource.jansi.AnsiConsole;

import java.util.InputMismatchException;

enum GameState {
    MAIN_MENU,
    GAME_MENU,
    OPTION_MENU,
    EXIT,
    RUNNING
}

public class Main {
    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        mainMenu();
//        int width = AnsiConsole.getTerminalWidth();
//        Scanner scanner = new Scanner(System.in);
//        String asd = scanner.next();
//        System.out.println(asd);
//        System.out.println("qwe");
//        Thread.sleep(1000);
//        System.out.println("zui");
//        Thread.sleep(1000);
//        System.out.println(ansi().eraseScreen().a("hjk"));
//        System.out.println(ansi().a("asdasdasdasdasdasd"));
//        Thread.sleep(1000);
//        System.out.println(ansi().eraseScreen().a("erqweqweqwe"));
//        Thread.sleep(2000);
    }

    private static void mainMenu() {
        GameState playerInput = GameState.MAIN_MENU;

        do {
            try {
                playerInput = CLI.renderMainMenu();
            } catch (InputMismatchException e) {
                System.out.println(ansi().eraseScreen().bg(RED).a("Invalid Input").reset());
            }
        } while (playerInput == GameState.MAIN_MENU);

        switch (playerInput) {
            case GameState.GAME_MENU -> gameMenu();
            case GameState.OPTION_MENU -> optionMenu();
            case GameState.EXIT -> System.exit(1);
        }
    }

    private static void gameMenu() {
        System.out.println("asdasdasd");
    }

    private static void optionMenu() {
        System.out.println();
    }
}
