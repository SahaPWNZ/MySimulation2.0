package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.Simulation;

public abstract class Creature extends Entity {
    private final int SPEED;



    Creature(int speed, Coordinates coordinates) {
        this.SPEED = speed;
        this.coordinates = coordinates;
    }

    public int getSPEED() {
        return SPEED;
    }







    abstract void makeMove(Simulation simulation, Coordinates oldCoordinates, Coordinates newCoordinates);

    public abstract boolean findEat(Simulation simulation);

    public abstract void randomMove(Simulation simulation);

    public abstract void eat(Simulation simulation);
}
