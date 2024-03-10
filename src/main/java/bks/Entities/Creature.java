package bks.Entities;

import bks.Action.InitAction.InitAction;
import bks.Main.Coordinates;
import bks.Main.GameMap;

import java.util.ArrayList;
import java.util.Map;

public abstract class Creature extends Entity {
public static ArrayList<? extends Creature> entities;
    Creature(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


    public abstract void eat(GameMap map); //поиск и поедание еды в соседней ячейке

    public Coordinates getCoordinatesOfClosestEat(GameMap map, ArrayList<Entity> listOfEat) {
        int selfVectorCoordinate = this.getCoordinates().getCol() + this.getCoordinates().getRow();
        int minEatVector = map.getHEIGHT() + map.getWIDTH() + 2;
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

    public static ArrayList<Entity> getListOfEats(Class<?> eat, GameMap map) { //метод возвращающий список заданного класса сущностей
        ArrayList<Entity> result = new ArrayList<>();
        for (Map.Entry<Coordinates, Entity> entry : map.getMap().entrySet()) {
            if (eat.isInstance(entry.getValue())) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public void makeMove(GameMap map, Coordinates oldCoordinates, Coordinates newCoordinates) {
        this.coordinates = newCoordinates;
        map.makeEmptyCeil(oldCoordinates);
        map.getMap().put(coordinates, this);
    }

    public boolean findEat(GameMap map, Class<?> foodClass) { //проверяет соседние ячейки на наличие еды
        int[][] array = coordinates.getArrayOfCoordinatesNeighbors();
        for (int[] pairOfCoord : array) {
            if (Coordinates.validCoordinates(new Coordinates(pairOfCoord[0], pairOfCoord[1]), map) &&
                    foodClass.isInstance(map.getMap().get(new Coordinates(pairOfCoord[0], pairOfCoord[1])))) {
                return true;
            }
        }
        return false;
    }

    public void randomMove(GameMap map) {
        ArrayList<Coordinates> listOfFreeCoordinates = new ArrayList<>();
        int[][] array = {{-1 + coordinates.getRow(), coordinates.getCol()},
                {1 + coordinates.getRow(), coordinates.getCol()},
                {coordinates.getRow(), coordinates.getCol() - 1},
                {coordinates.getRow(), coordinates.getCol() + 1}};
        for (int[] pairOfCoord : array) {
            if (Coordinates.validCoordinates(new Coordinates(pairOfCoord[0], pairOfCoord[1]), map)
                    && map.isEmptyCeil(new Coordinates(pairOfCoord[0], pairOfCoord[1]))) {
                listOfFreeCoordinates.add(new Coordinates(pairOfCoord[0], pairOfCoord[1]));
            }
            if (!listOfFreeCoordinates.isEmpty()) {
                map.makeEmptyCeil(this.coordinates);
                this.coordinates = listOfFreeCoordinates.get(InitAction.random.nextInt(listOfFreeCoordinates.size()));
                map.getMap().put(this.coordinates, this);
            }
        }
    }

}