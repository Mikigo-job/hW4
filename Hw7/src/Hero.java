public abstract class Hero implements HavingSuperAbility {
    private int health;
    private int damage;
    private String superAbilityType;

    public Hero(int health, int damage, String superAbilityType) {
        this.health = health;
        this.damage = damage;
        this.superAbilityType = superAbilityType;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public String getSuperAbilityType() {
        return superAbilityType;
    }
}
