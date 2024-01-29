package bks.Entities;

import bks.Main.Coordinates;

public final class Grass extends Entity{
    public Grass(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "G";
    }
}
