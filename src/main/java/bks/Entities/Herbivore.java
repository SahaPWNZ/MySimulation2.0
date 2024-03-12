package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.GameMap;

import java.util.ArrayList;

public final class Herbivore extends Creature {
    public static ArrayList<Herbivore> entities = new ArrayList<>(); // Лист хранящий травоядных

    public Herbivore(Coordinates coordinates) {
        super (coordinates);
        entities.add(this);
    }

    @Override
    public void eat(GameMap map) {
        int[][] array = coordinates.getArrayOfCoordinatesNeighbors();
        for (int[] pairOfCoord : array) {
            if (map.getMap().get(new Coordinates(pairOfCoord[0], pairOfCoord[1])) instanceof Grass) {
                map.makeEmptyCeil(new Coordinates(pairOfCoord[0], pairOfCoord[1]));
            }
        }
    }

    @Override
    public String toString() {
        return "H";
    }
}
