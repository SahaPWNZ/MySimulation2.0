package bks.saha.Entities;

public final class Predator extends Creature {
    final int DAMAGE;
// координаты
    Predator(int speed, int hp, int damage) {
        super(speed, hp);
        this.DAMAGE = damage;
    }

    @Override
    void makeMove() {

    }

    void atack() {

    }
}
