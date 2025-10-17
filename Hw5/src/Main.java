public class Main {
    public static void main(String[] args) {
        // бос
        Boss boss = new Boss();
        boss.setHealth(700);
        boss.setDamage(50);
        boss.setDefenceType("Magic");

        System.out.println("=== Информация о Боссе ===");
        System.out.println("Здоровье: " + boss.getHealth());
        System.out.println("Урон: " + boss.getDamage());
        System.out.println("Тип защиты: " + boss.getDefenceType());
        System.out.println();

        // герой
        Hero[] heroes = createHeroes();

        System.out.println("=== Информация о Героях ===");
        for (int i = 0; i < heroes.length; i++) {
            System.out.println("Герой " + (i + 1) + ":");
            System.out.println("  Здоровье: " + heroes[i].getHealth());
            System.out.println("  Урон: " + heroes[i].getDamage());
            System.out.println("  Суперспособность: " + heroes[i].getSuperPower());
            System.out.println();
        }
    }

    // новые герои
    public static Hero[] createHeroes() {
        Hero hero1 = new Hero(250, 20, "Молния");
        Hero hero2 = new Hero(300, 15);
        Hero hero3 = new Hero(200, 25, "Огненное дыхание");

        Hero[] heroes = {hero1, hero2, hero3};
        return heroes;
    }
}
