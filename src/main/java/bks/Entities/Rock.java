package bks.Entities;

import bks.Main.Coordinates;

public final class Rock extends Entity{
    public Rock(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "R";
    }
}
