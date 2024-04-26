package cli.menu;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.*;

public class OptionMenu {

    public static void run() {
        int playerInput;

        do {
            printOptionMenu();
            playerInput = Menu.getUserInput();
        } while (!(playerInput >= 1 && playerInput <= 3));

        switch (playerInput) {
            case 1 -> MainMenu.run();
            case 2 -> System.exit(1);
        }
    }

    private static void printOptionMenu() {
        AnsiConsole.systemInstall();
        for (int i = 0; i < 5; i++) {
            System.out.print(ansi()
                    .cursor(0,0)
                    .eraseScreen()
                    .render(String.valueOf(i))
                    .cursorLeft(String.valueOf(i).length())
            );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
