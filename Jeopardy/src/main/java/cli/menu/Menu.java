package cli.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

interface Menu {

    static int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        }
    }

}
