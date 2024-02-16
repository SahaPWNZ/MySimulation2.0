package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.Simulation;

import java.util.ArrayList;
import java.util.Map;

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

    public static ArrayList<Entity> getListOfEats(Class<?> eat, Simulation simulation) { //метод возвращающий список заданного класса сущностей
        ArrayList<Entity> result = new ArrayList<>();
        for (Map.Entry<Coordinates, Entity> entry : simulation.getGameMap().getMap().entrySet()) {
            if (eat.isInstance(entry.getValue())) {
                result.add(entry.getValue());
            }
        }
        return result;
    }
}