
enum WeaponType {
    SWORD, BOW, MAGIC
}

// 2Weapon
class Weapon {
    private WeaponType weaponType;
    private String name;
    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class GameEntity {
    private int health;
    private int damage;
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
class Boss extends GameEntity {
    private Weapon weapon;
    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public String printInfo() {
        return "Boss — здоровье: " + getHealth() +
                ", урон: " + getDamage() +
                ", оружие: " + weapon.getName() +
                " (" + weapon.getWeaponType() + ")";
    }
}
class Skeleton extends Boss {
    private int arrowsCount;
    public int getArrowsCount() {
        return arrowsCount;
    }
    public void setArrowsCount(int arrowsCount) {
        this.arrowsCount = arrowsCount;
    }
    @Override
    public String printInfo() {
        return "Skeleton — здоровье: " + getHealth() +
                ", урон: " + getDamage() +
                ", оружие: " + getWeapon().getName() +
                " (" + getWeapon().getWeaponType() + ")" +
                ", количество стрел: " + arrowsCount;
    }
}
public class Main {
    public static void main(String[] args) {
        // Создаём оружие для босса
        Weapon bossWeapon = new Weapon();
        bossWeapon.setName("Огненный меч");
        bossWeapon.setWeaponType(WeaponType.SWORD);

        // Создаём босса
        Boss boss = new Boss();
        boss.setHealth(1000);
        boss.setDamage(80);
        boss.setWeapon(bossWeapon);

        System.out.println(boss.printInfo());

        // Создаём 2 скелетов
        Weapon bow = new Weapon();
        bow.setName("Лук тьмы");
        bow.setWeaponType(WeaponType.BOW);

        Skeleton skeleton1 = new Skeleton();
        skeleton1.setHealth(150);
        skeleton1.setDamage(25);
        skeleton1.setWeapon(bow);
        skeleton1.setArrowsCount(30);

        Skeleton skeleton2 = new Skeleton();
        skeleton2.setHealth(200);
        skeleton2.setDamage(35);
        skeleton2.setWeapon(bow);
        skeleton2.setArrowsCount(50);

        System.out.println(skeleton1.printInfo());
        System.out.println(skeleton2.printInfo());
    }
}
