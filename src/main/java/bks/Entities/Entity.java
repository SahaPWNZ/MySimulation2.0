package bks.Entities;

import bks.Main.Coordinates;

public abstract class Entity {
    Coordinates coordinates;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


}
