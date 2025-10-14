import java.util.Random;

public class hW4 {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int roundNumber;
    public static String message;

    public static int[] heroesHealth = {290, 270, 250, 300, 700, 280};
    public static int[] heroesDamage = {20, 15, 10, 0, 10, 15};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky"};

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int health : heroesHealth) {
            if (health > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossAttacks();
        heroesAttack();
        medicHeals();
        printStatistics();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(3); // защита босса только от 3 типов атакующих
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void bossAttacks() {
        Random random = new Random();
        boolean luckyDodged = random.nextInt(100) < 25; // 25% шанс уклониться
        int golemIndex = 4;
        int luckyIndex = 5;

        int golemDamageShare = 0;

        // Golem получает 1/5 урона от атак, направленных на других живых героев
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i != golemIndex && heroesHealth[i] > 0 && heroesHealth[golemIndex] > 0) {
                golemDamageShare += bossDamage / 5;
            }
        }

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (i == luckyIndex && luckyDodged) {
                    System.out.println("Lucky уклонился от удара босса!");
                    continue;
                }
                heroesHealth[i] -= bossDamage;
                if (heroesHealth[i] < 0) heroesHealth[i] = 0;
            }
        }

        // Golem получает весь дополнительный урон
        if (heroesHealth[golemIndex] > 0) {
            heroesHealth[golemIndex] -= golemDamageShare;
            if (heroesHealth[golemIndex] < 0) heroesHealth[golemIndex] = 0;
        }
    }

    public static void heroesAttack() {
        message = null;
        Random random = new Random();
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0 && heroesDamage[i] > 0) {
                int damage = heroesDamage[i];
                if (bossDefence.equals(heroesAttackType[i])) {
                    int coefficient = random.nextInt(2, 11); // 2-10
                    damage *= coefficient;
                    message = "Critical damage: " + damage + " from " + heroesAttackType[i];
                }
                bossHealth -= damage;
                if (bossHealth < 0) bossHealth = 0;
            }
        }
    }

    public static void medicHeals() {
        int medicIndex = 3;
        if (heroesHealth[medicIndex] <= 0) return; // Медик мёртв

        int healAmount = 80;
        int targetIndex = -1;
        int lowestHealth = Integer.MAX_VALUE;

        // Находим живого героя (кроме медика), у которого HP < 100
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i != medicIndex && heroesHealth[i] > 0 && heroesHealth[i] < 100) {
                if (heroesHealth[i] < lowestHealth) {
                    lowestHealth = heroesHealth[i];
                    targetIndex = i;
                }
            }
        }

        if (targetIndex != -1) {
            heroesHealth[targetIndex] += healAmount;
            System.out.println("Medic вылечил " + heroesAttackType[targetIndex] +
                    " на " + healAmount + " HP!");
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ------------------");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage +
                " defence: " + (bossDefence == null ? "No defence" : bossDefence));

        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i] +
                    " damage: " + heroesDamage[i]);
        }

        if (message != null) {
            System.out.println(message);
        }
    }
}
