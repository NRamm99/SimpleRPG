import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Game.start();
        promptMainMenu();

    }

    private static void promptMainMenu() {
        while (!Game.gameOver) {
            Tools.clearConsole();
            Game.printGameTitle();
            Tools.titlePrinter("MAIN MENU");
            System.out.println("1... Fight random enemy");
            System.out.println("2... My stats");
            System.out.println("3... Raise difficulty (Recommended level -> " + Game.getRecommendedLevel() + ")");
            System.out.println("\n0... Quit");

            int userInput;
            while (true) {
                userInput = Tools.validateInt(input);
                if (userInput < 0 || userInput > 3) {
                    System.out.println("❌ Please enter a valid number.");
                } else {
                    break;
                }
            }

            switch (userInput) {
                case 1:
                    Game.fightRandomEnemy();
                    break;
                case 2:
                    Game.printStats();
                    break;
                case 3:
                    Game.raiseDifficulty();
                    break;
                case 0:
                    return;
                default:
            }
        }
        Tools.printToConsole("GAME OVER... Better luck next time...", true);

    }


}
