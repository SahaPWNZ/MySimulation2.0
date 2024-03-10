package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.GameMap;

import java.util.ArrayList;

public final class Predator extends Creature {

    public static ArrayList<Predator> entities = new ArrayList<>(); //лист хищников

    public Predator(Coordinates coordinates) {
        super( coordinates);
        entities.add(this);
    }




    @Override
    public String toString() {
        return "P";
    }

    @Override
    public void eat(GameMap map) {
        int[][] array = this.coordinates.getArrayOfCoordinatesNeighbors();
        for (int[] pairOfCoord : array) {
            for (Herbivore herbivore : Herbivore.entities) {
                if (herbivore.getCoordinates().equals(new Coordinates(pairOfCoord[0], pairOfCoord[1]))) {
                    Herbivore.entities.remove(herbivore);
                    map.makeEmptyCeil(new Coordinates(pairOfCoord[0], pairOfCoord[1]));
                    break;
                }
            }
        }
    }
}

