package bks.Action.InitAction;

import bks.Action.Action;
import bks.Entities.*;
import bks.Main.Coordinates;
import bks.Main.GameMap;

import java.util.Random;

public abstract class InitAction extends Action {
    protected final static int HERBIVORE_COUNT = 3;
    protected final static int PREDATOR_COUNT = 1;
    protected final static int GRASS_COUNT = 4;
    protected final static int ROCK_COUNT = 2;
    protected final static int TREE_COUNT = 2;
    public static Random random = new Random();

    public abstract void makeAction(GameMap map);

    public void initHerbivore(GameMap map, int count) {
        while (count < HERBIVORE_COUNT) {
            Coordinates coordinates = new Coordinates(random.nextInt(map.getHEIGHT()), random.nextInt(map.getWIDTH()));
            if (map.isEmptyCeil(coordinates)) {
                map.getMap().put(coordinates, new Herbivore(coordinates));
                count++;
            }
        }
    }

    public void initPredator(GameMap map, int count) {
        while (count < PREDATOR_COUNT) {
            Coordinates coordinates = new Coordinates(random.nextInt(map.getHEIGHT()), random.nextInt(map.getWIDTH()));
            if (map.isEmptyCeil(coordinates)) {
                map.getMap().put(coordinates, new Predator(coordinates));
                count++;
            }
        }
    }

    public void initGrass(GameMap map, int count) {
        while (count < GRASS_COUNT) {
            Coordinates coordinates = new Coordinates(random.nextInt(map.getHEIGHT()), random.nextInt(map.getWIDTH()));
            if (map.isEmptyCeil(coordinates)) {
                map.getMap().put(coordinates, new Grass(coordinates));
                count++;
            }
        }
    }

    public void initRock(GameMap map, int count) {
        while (count < ROCK_COUNT) {
            Coordinates coordinates = new Coordinates(random.nextInt(map.getHEIGHT()), random.nextInt(map.getWIDTH()));
            if (map.isEmptyCeil(coordinates)) {
                map.getMap().put(coordinates, new Rock(coordinates));
                count++;
            }
        }
    }

    public void initTree(GameMap map, int count) {
        while (count < TREE_COUNT) {
            Coordinates coordinates = new Coordinates(random.nextInt(map.getHEIGHT()), random.nextInt(map.getWIDTH()));
            if (map.isEmptyCeil(coordinates)) {
                map.getMap().put(coordinates, new Tree(coordinates));
                count++;
            }
        }
    }

}
