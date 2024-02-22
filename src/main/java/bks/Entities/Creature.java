package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.GameMap;
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

    public abstract void makeMove(GameMap map, Coordinates oldCoordinates, Coordinates newCoordinates);//метод отвечающий за перемещение

    public abstract boolean findEat(GameMap map); //проверяет соседние ячейки на наличие еды

    public abstract void randomMove(Simulation simulation); // метод перемещающий в рандомную свободную соседнюю ячейку

    public abstract void eat(Simulation simulation); //поиск и поедание еды в соседней ячейке
    public Coordinates getCoordinatesOfClosestEat(Simulation simulation, ArrayList<Entity> listOfEat){
        int selfVectorCoordinate = this.getCoordinates().getCol() + this.getCoordinates().getRow();
        int minEatVector = simulation.getGameMap().getHEIGHT() + simulation.getGameMap().getWIDTH() + 2;
        Coordinates result = null;
        for (Entity entity : listOfEat) {
            int eatVectorCoordinate = entity.getCoordinates().getCol() + entity.getCoordinates().getRow();
            if (minEatVector > Math.abs(eatVectorCoordinate - selfVectorCoordinate)) {
                minEatVector = Math.abs(eatVectorCoordinate - selfVectorCoordinate);
                result = entity.getCoordinates();
            }
        }
        return result;
    }

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