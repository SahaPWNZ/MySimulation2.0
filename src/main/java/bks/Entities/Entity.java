package bks.Entities;

import bks.Main.Coordinates;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Entity {
    Coordinates coordinates;


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
