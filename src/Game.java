import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Enemy> enemies = new ArrayList<>();
    static Player player;
    public static boolean gameOver = false;
    public static boolean gameWin = false;
    static int difficulty = 1;
    static final int BEGINNER = 1;
    static final int EASY = 2;
    static final int NORMAL = 3;
    static final int HARD = 4;
    static final int BOSS = 5;


    public static void start() {
        addEnemies();

        player = promptChooseName();


    }

    private static Player promptChooseName() {
        while (true) {
            Tools.clearConsole();
            System.out.println("Tip: Do not trust ANYONE.");
            printGameTitle();
            System.out.println("Welcome to SimpleRPG!"
                    + "\nWhat is your name, hero?");
            System.out.print("\nMy name is: ");
            String name = input.nextLine();

            Tools.clearConsole();
            System.out.println("You have entered " + name + " as your name."
                    + "\nAre you sure you want this name?"
                    + "\n1... Yes"
                    + "\n2... No");
            int userInput = Tools.validateInt(input);
            if (userInput == 1) {
                return new Player(name, 100, 20);
            }
        }
    }


    public static int getRecommendedLevel() {

        return switch (difficulty) {
            // EASY
            case 1 -> 2;

            // NORMAL
            case 2 -> 3;

            // HARD
            case 3 -> 5;

            // BOSS
            case 4 -> 6;

            // INVALID INPUT
            default -> 0;
        };
    }

    private static void addEnemies() {

        enemies.add(new Enemy("Goblin", 30, 5, BEGINNER, 20, false));
        enemies.add(new Enemy("Skeleton", 35, 7, BEGINNER, 25, false));

        enemies.add(new Enemy("Zombie", 45, 8, EASY, 35, false));
        enemies.add(new Enemy("Orc", 55, 10, EASY, 40, false));
        enemies.add(new Enemy("Bandit", 60, 12, EASY, 45, false));

        enemies.add(new Enemy("Dark Knight", 75, 15, NORMAL, 60, false));
        enemies.add(new Enemy("Fire Mage", 65, 18, NORMAL, 70, false));

        enemies.add(new Enemy("Troll", 90, 20, HARD, 85, false));
        enemies.add(new Enemy("Vampire", 80, 17, HARD, 90, false));

        enemies.add(new Enemy("Dragon", 130, 25, BOSS, 120, true));
    }

    public static void printGameTitle() {
        System.out.println("""
                  _________.__               .__        ____________________  ________\s
                 /   _____/|__| _____ ______ |  |   ____\\______   \\______   \\/  _____/\s
                 \\_____  \\ |  |/     \\\\____ \\|  | _/ __ \\|       _/|     ___/   \\  ___\s
                 /        \\|  |  Y Y  \\  |_> >  |_\\  ___/|    |   \\|    |   \\    \\_\\  \\
                /_______  /|__|__|_|  /   __/|____/\\___  >____|_  /|____|    \\______  /
                        \\/          \\/|__|             \\/       \\/                  \\/\s""");

    }

    public static void fightRandomEnemy() {
        Enemy enemy = pickRandomEnemy();

        Tools.clearConsole();
        printGameTitle();
        Tools.printToConsole("You've encountered a " + enemy.getName());
        Tools.printToConsole(printBattleStatus(player, enemy));
        Tools.waitForUser(input);

        while (player.isAlive() && enemy.isAlive()) {
            int dmg;
            Tools.clearConsole();
            dmg = player.getDmg();
            player.attack(enemy, dmg);
            Tools.printToConsole(printBattleStatus(player, enemy));
            Tools.printToConsole("⚔ " + player.getName() + " attacks " + enemy.getName() + " and deals " + dmg + " dmg! ⚔");
            Tools.waitForUser(input);

            if (!enemy.isAlive()) {
                break;
            }

            Tools.clearConsole();
            dmg = enemy.getDmg();
            enemy.attack(player, dmg);
            Tools.printToConsole(printBattleStatus(player, enemy));
            Tools.printToConsole("⚔ " + enemy.getName() + " attacks " + player.getName() + " and deals " + dmg + " dmg! ⚔");
            Tools.waitForUser(input);
        }
        if (!player.isAlive()) {
            gameOver = true;
        } else {
            Tools.clearConsole();
            Tools.printToConsole(printBattleStatus(player, enemy));
            Tools.printToConsole("\n\uD83C\uDFC6 You've defeated the " + enemy.getName() + "! \uD83C\uDFC6");
            player.gainXp(enemy.getXpReward());
            Tools.waitForUser(input);

            int rollDropChance = (int) ((Math.random() * 100) + 1);
            if (rollDropChance > 70) {
                player.giveHealPotion();
            }

            enemy.heal();
        }

        if (enemy.isDragon && !enemy.isAlive()) {
            promptGameWin();
        }

    }

    private static void promptGameWin() {
        Tools.printToConsole("✨ You've slain the dragon. ✨" +
                "✨ This means you've won the game! ✨" +
                "✨ Amazing job hero! ✨");
        Tools.waitForUser(input);
        gameWin = true;
    }

    public static String printBattleStatus(Player player, Enemy enemy) {

        return "\n=================== ⚔ BATTLE ⚔ ===================\n" +
                String.format("%-25s | %-25s%n", "\uD83E\uDDD9" + player.getName(), "\uD83D\uDC79" + enemy.getName()) +
                String.format("❤️ HP: %-19d | ❤️ HP: %-19d%n",
                        player.getHealth(), enemy.getHealth()) +
                "==================================================\n";
    }

    private static Enemy pickRandomEnemy() {
        Random random = new Random();

        ArrayList<Enemy> matchingEnemy = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (enemy.getDifficulty() == difficulty) {
                matchingEnemy.add(enemy);
            }
        }

        int randomIndex = random.nextInt(matchingEnemy.size());
        return matchingEnemy.get(randomIndex);
    }

    public static void printStats() {
        Tools.titlePrinter(player.getName(), true);
        Tools.printToConsole("HP: " + player.getMaxHP() + " (" + player.getHealth() + ")" +
                "\nATK: " + player.getAttackPower() +
                "\nLevel: " + player.getLevel() + " (xp: " + player.getXp() + "/" + player.getXpNeeded() + ")");
        Tools.waitForUser(input);
    }

    public static void raiseDifficulty() {
        if (difficulty < BOSS) {
            difficulty++;
            Tools.printToConsole("You've successfully raised the difficulty. Good luck.", true);
            Tools.waitForUser(input);
        } else {
            Tools.printToConsole("You can't raise the difficulty any further. You've got to slay the dragon.", true);
            Tools.waitForUser(input);
        }
    }
}
