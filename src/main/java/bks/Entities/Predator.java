package bks.Entities;

import bks.Main.Coordinates;

public final class Predator extends Creature {

    public Predator(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String toString() {
        return "P";
    }

}

