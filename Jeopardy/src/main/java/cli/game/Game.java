package cli.game;

import cli.CLI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

    private static final String NUMBER_OF_PLAYERS = String.valueOf(
                    ansi()
                    .cursor(0,0)
                    .eraseScreen()
                    .a("Create Players:")
                    .newline()
                    .newline()
                    .a("Number of Players (2-4): ")
    );

    private static final String CONTINUE_MENU = String.valueOf(
                    ansi()
                    .a("Do you want to continue?")
                    .newline()
                    .a("[1] Yes")
                    .newline()
                    .a("[2] Reset")
                    .newline()
                    .newline()
                    .saveCursorPosition()
    );

    private static List<String> playerNames = new ArrayList<>();

    public static void playerCreation() {
        int numberOfPlayers;
        boolean finishedPlayerCreation;

        do {
            playerNames.clear();
            do {
                System.out.print(NUMBER_OF_PLAYERS);
                numberOfPlayers = CLI.getUserInput();
            } while (!(numberOfPlayers >= 2 && numberOfPlayers <= 4));

            System.out.print(ansi().newline().newline());

            for (int i = 1; i <= numberOfPlayers; i++) {
                System.out.print(ansi().a("Name Player " + i + ": "));

                String name = new Scanner(System.in).next();
                playerNames.add(name);

                System.out.print(ansi().newline());
            }

            finishedPlayerCreation = (continueMenu() == 2);

        } while (finishedPlayerCreation);

        playerNames.forEach(n -> System.out.print(ansi().a(n).newline()));

    }

    private static int continueMenu() {
        int input;
        boolean continueMenu = false;

        System.out.print(CONTINUE_MENU);

        do {
            if (continueMenu) {
                System.out.print(ansi()
                                .cursorUpLine()
                                .eraseLine()
                                .restoreCursorPosition()
                                .eraseLine()
                                .a("Invalid Option! Try Again:")
                                .newline()
                );
            }
            input = CLI.getUserInput();
            continueMenu = !(input >= 1 && input <= 2);
        } while (continueMenu);

        return  input;
    }

}
