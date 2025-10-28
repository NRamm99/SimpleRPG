public class Enemy {
    private final String name;
    private int health;
    private final int maxHP;
    private final int attackPower;
    private final int difficulty;
    private final int xpReward;
    public final boolean isDragon;

    public Enemy(String name, int health, int attackPower, int difficulty, int xpReward, boolean isDragon) {
        this.name = name;
        this.health = health;
        this.maxHP = health;
        this.attackPower = attackPower;
        this.difficulty = difficulty;
        this.xpReward = xpReward;
        this.isDragon = isDragon;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public int getDmg(){
        return (int) ((Math.random() * attackPower) + 1);
    }

    public void attack(Player player, int dmg) {
        player.takeDamage(dmg);
    }

    public int getXpReward() {
        return xpReward;
    }

    public void heal() {
        health = maxHP;
    }

    public int getDifficulty() {
        return difficulty;
    }

}
