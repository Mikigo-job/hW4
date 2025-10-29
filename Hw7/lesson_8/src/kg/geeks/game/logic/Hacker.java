package kg.geeks.game.players;

import kg.geeks.game.logic.RPG_Game;
import java.util.Random;

public class Hacker extends Hero {
    private final int stealAmount; 
    private final Random random = new Random();

    public Hacker(String name, int health, int damage, int stealAmount) {
        super(name, health, damage);
        this.stealAmount = stealAmount;
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (this.getHealth() <= 0 || boss.getHealth() <= 0) {
            return;
        }

        int round = RPG_Game.getRoundNumber();
        if (round % 2 != 0) { // если хотим, чтобы действие было на чётных — пропускаем нечётные
            return;
        }

        int aliveCount = 0;
        for (Hero h : heroes) {
            if (h != null && h.getHealth() > 0 && h != this) aliveCount++;
        }
        if (aliveCount == 0) {
            return;
        }

        Hero receiver = null;
        while (receiver == null) {
            Hero candidate = heroes[random.nextInt(heroes.length)];
            if (candidate != null && candidate != this && candidate.getHealth() > 0) {
                receiver = candidate;
            }
        }

        int actualSteal = Math.min(stealAmount, boss.getHealth());
        if (actualSteal <= 0) {
            return;a
        }

        boss.setHealth(boss.getHealth() - actualSteal);
        receiver.setHealth(receiver.getHealth() + actualSteal);

        System.out.println(this.getName() + " похитил " + actualSteal +
                " HP у босса и передал " + receiver.getName() + "!");
    }
}
