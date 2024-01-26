package bks.Entities;

import bks.Main.Coordinates;

public final class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates) {

        super(1, 10, coordinates);
    }

    @Override
    void makeMove() {
    }

    @Override
    public String toString() {
        return "H";
    }
}
