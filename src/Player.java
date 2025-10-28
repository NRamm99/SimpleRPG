
public class Player {
    private final String name;
    private int health;
    private int attackPower;
    private int maxHP;
    private int xp;
    private int level;
    private int xpNeeded;
    private int healingPotionAmount;

    public Player(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.maxHP = health;
        this.xp = 0;
        this.level = 1;
        this.xpNeeded = 100;
        this.healingPotionAmount = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getXp() {
        return xp;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHealingPotionAmount() {
        return healingPotionAmount;
    }

    public void giveHealPotion() {
        healingPotionAmount++;
    }

    public void attack(Enemy enemy, int dmg) {
        enemy.takeDamage(dmg);
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDmg() {
        return (int) ((Math.random() * attackPower) + 1);
    }

    public int getXpNeeded() {
        return xpNeeded;
    }

    public void heal() {
        health = maxHP;
    }

    public void drinkPotion() {
        health = maxHP;
        healingPotionAmount--;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    private void levelUp() {
        xp -= level * 100;
        level++;
        maxHP += 15;
        attackPower += 3;
        heal();
        System.out.println("✨ " + name + " leveled up to level " + level + "! ✨");
        System.out.println("Max HP: " + maxHP + " | Attack Power: " + attackPower);
    }

    public void gainXp(int xpReward) {
        xp += xpReward;
        System.out.println(name + " gained " + xpReward + " XP! (" + xp + " total)");

        xpNeeded = level * 100;
        if (xp >= xpNeeded) {
            levelUp();
        }
    }
}
