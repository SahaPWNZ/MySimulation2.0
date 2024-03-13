package bks.Entities;

import bks.Main.Coordinates;

public final class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }


    @Override
    public String toString() {
        return "H";
    }
}
