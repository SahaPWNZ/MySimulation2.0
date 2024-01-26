package bks.Entities;

import bks.Main.Coordinates;

public final class Predator extends Creature {
    final int DAMAGE;

    // координаты
    public Predator(Coordinates coordinates) {
        super(2, 10, coordinates);
        this.DAMAGE = 5;
    }

    @Override
    void makeMove() {

    }

    void atack() {

    }
}
