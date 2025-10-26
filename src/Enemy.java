public class Enemy {
    private final String name;
    private int health;
    private final int maxHP;
    private final int attackPower;
    private final int difficulty;
    private final int xpReward;

    public Enemy(String name, int health, int attackPower, int difficulty, int xpReward) {
        this.name = name;
        this.health = health;
        this.maxHP = health;
        this.attackPower = attackPower;
        this.difficulty = difficulty;
        this.xpReward = xpReward;
    }

    public String attack(Player player, Enemy enemy) {
        return "⚔" + enemy.name + " attacks " + player.getName() + "⚔"
                + "\n⚔ It deals " + enemy.attackPower + " dmg! ⚔";
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

    public void takeDamage(int damage){
        health -= damage;
    }

    public int getAttackPower(){
        return attackPower;
    }

    public void attack(Player player){
        player.takeDamage(attackPower);
    }

    public int getXpReward(){
        return xpReward;
    }

    public void heal() {
        health = maxHP;
    }

}
