public class Medic extends Hero {
    private int healPoints;

    public Medic(int health, int damage, String superAbilityType, int healPoints) {
        super(health, damage, superAbilityType);
        this.healPoints = healPoints;
    }

    public int getHealPoints() {
        return healPoints;
    }

    public void increaseExperience() {
        healPoints += healPoints * 0.1;
        System.out.println("Medic увеличил опыт лечения, теперь лечит на " + healPoints + " единиц.");
    }

    @Override
    public void applySuperAbility() {
        System.out.println("Medic применил супер способность " + getSuperAbilityType());
    }
}
