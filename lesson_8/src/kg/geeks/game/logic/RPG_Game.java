package kg.geeks.game.logic;

import kg.geeks.game.players.*;

import java.util.Random;

public class RPG_Game {

    public static Random random = new Random();
    private static int roundNumber;

    public static int getRoundNumber() {
        return roundNumber;
    }


    public static void startGame() {
        Boss boss = new Boss("Aleksey", 1000, 50);

        Warrior warrior1 = new Warrior("Bob", 280, 20);
        Warrior warrior2 = new Warrior("Arthur", 270, 15);
        Magic magic = new Magic("Jane", 290, 10);
        Berserk berserk = new Berserk("John", 260, 10);
        Medic doc = new Medic("Hendolf", 200, 5, 15);
        Medic assistant = new Medic("Peter", 300, 5, 5);
        Hacker hacker = new Hacker("Hacker", 240, 15, 30); // 30 — N, меняй по желанию


        Hero[] heroes = {warrior1, doc, warrior2, berserk, magic, assistant, hacker};

        printStatistics(boss, heroes);
        while (!isGameOver(boss, heroes)) {
            playRound(boss, heroes);
        }
    }

    private static void printStatistics(Boss boss, Hero[] heroes) {
        System.out.println("Round " + roundNumber + " ------------------");
        System.out.println(boss);
        for (Hero hero : heroes) {
            System.out.println(hero);
        }
    }

    private static boolean isGameOver(Boss boss, Hero[] heroes) {
        if (boss.getHealth() <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        boolean allHeroesDead = true;
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!");
            return true;
        }
        return false;
    }

    private static void playRound(Boss boss, Hero[] heroes) {
        roundNumber++;
        boss.chooseDefence();
        boss.attack(heroes);
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0 && boss.getHealth() > 0 &&
            boss.getDefence() != hero.getAbility()) {
                hero.attack(boss);
                hero.applySuperPower(boss, heroes);
            }
        }
        printStatistics(boss, heroes);
    }
}
