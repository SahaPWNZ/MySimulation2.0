package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.Simulation;

public final class Predator extends Creature {
    final int DAMAGE;

    // координаты
    public Predator(Coordinates coordinates) {
        super(2, 10, coordinates);
        this.DAMAGE = 5;
    }

    @Override
    void makeMove(Simulation simulation, Coordinates oldCoordinates, Coordinates newCoordinates) {

    }

    @Override
    public boolean findEat(Simulation simulation) {
        return false;
    }

    @Override
    public void randomMove(Simulation simulation) {

    }

    @Override
    public void eat(Simulation simulation) {

    }


    void atack() {

    }
}
