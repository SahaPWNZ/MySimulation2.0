package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.GameMap;

import java.util.ArrayList;

public final class Predator extends Creature {

    public Predator(Coordinates coordinates) {
        super( coordinates);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public void eat(GameMap map) { // поедание пищи с соседней координаты
        int[][] array = coordinates.getArrayOfCoordinatesNeighbors();
        for (int[] pairOfCoord : array) {
            ArrayList<Entity> listHerbivore = Creature.getListOfEntity(Herbivore.class, map);
            for (Entity herbivore : listHerbivore) {
                if (herbivore.getCoordinates().equals(new Coordinates(pairOfCoord[0], pairOfCoord[1]))) {
                    map.makeEmptyCeil(new Coordinates(pairOfCoord[0], pairOfCoord[1]));
                    break;
                }
            }
        }
    }
}

