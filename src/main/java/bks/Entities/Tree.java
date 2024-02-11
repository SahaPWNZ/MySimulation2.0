package bks.Entities;

import bks.Main.Coordinates;

public class Tree extends Entity {
    public Tree(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "T";
    }
}
