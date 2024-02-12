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

    abstract void makeMove(Simulation simulation, Coordinates oldCoordinates, Coordinates newCoordinates);//метод отвечающий за перемещение

    public abstract boolean findEat(Simulation simulation); //проверяет соседние ячейки на наличие еды

    public abstract void randomMove(Simulation simulation); // метод перемещающий в рандомную свободную соседнюю ячейку

    public abstract void eat(Simulation simulation); //поиск и поедание еды в соседней ячейке
}
