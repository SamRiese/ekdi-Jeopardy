package cli;

import org.fusesource.jansi.AnsiConsole;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public interface CLI {

    static int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    static void printMenu(boolean invalidInput, String menu) {
        AnsiConsole.systemInstall();
        System.out.print(menu);

        if (invalidInput) {
            System.out.print(ansi()
                            .a("Invalid Option! Try Again:")
                            .newline()
            );
        }
    }
}
