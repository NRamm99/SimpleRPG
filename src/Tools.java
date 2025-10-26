import java.util.Scanner;
public class Tools {

    public static void clearConsole() {
        for (int n = 0; n < 20; n++) {
            System.out.println();
        }
    }

    public static void waitForUser(Scanner input) {
        System.out.println("\nPress enter to continue...");
        input.nextLine();
    }

    public static void printToConsole(String text, boolean clear) {
        if (clear) {
            clearConsole();
        }
        System.out.println(text);
    }

    public static void printToConsole(String text) {
        printToConsole(text, false);
    }

    public static void printToConsole(int number) {
        printToConsole(String.valueOf(number), false);
    }

    public static void titlePrinter(String title) {
        titlePrinter(title, false);
    }

    public static void titlePrinter(String title, boolean clear) {
        printToConsole("---------- " + title + " ----------", clear);
    }

    public static int validateInt(Scanner input) {
        while (true) {
            System.out.print("\nInput: ");
            String str = input.nextLine().trim();
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                printToConsole("❌ Please enter a valid number.");
            }
        }
    }

}
