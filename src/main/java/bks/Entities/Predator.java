package bks.Entities;

import bks.Main.Coordinates;

public final class Predator extends Creature {
    final int DAMAGE;

    // координаты
    public Predator(int speed, int hp, int damage, Coordinates coordinates) {
        super(speed, hp, coordinates);
        this.DAMAGE = damage;
    }

    @Override
    void makeMove() {

    }

    void atack() {

    }
}
