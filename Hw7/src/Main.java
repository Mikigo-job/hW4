public class Main {
    public static void main(String[] args) {
        Hero[] heroes = {
                new Warrior(300, 30, "CRITICAL DAMAGE"),
                new Magic(250, 40, "MAGICAL ATTACK"),
                new Medic(220, 0, "HEAL TEAMMATES", 50)
        };

        for (Hero hero : heroes) {
            hero.applySuperAbility();

            // Проверяем, является ли герой медиком
            if (hero instanceof Medic) {
                ((Medic) hero).increaseExperience();
            }
        }
    }
}
