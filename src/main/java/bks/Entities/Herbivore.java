package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.GameMap;
import bks.Main.Simulation;

import java.util.ArrayList;

public final class Herbivore extends Creature {
    public static ArrayList<Herbivore> entities = new ArrayList<>(); // Лист хранящий травоядных

    public Herbivore(Coordinates coordinates) {
        super(1, coordinates);
        entities.add(this);
    }

    @Override
    public void makeMove(GameMap map, Coordinates oldCoordinates, Coordinates newCoordinates) {
        this.coordinates = newCoordinates;
        map.makeEmptyCeil(oldCoordinates);
        map.getMap().put(this.coordinates, this);
    }

    @Override
    public boolean findEat(GameMap map) {
        int i = coordinates.getRow() - 1;
        int j = coordinates.getCol();
        if (i < map.getHEIGHT() && i >= 0 && j < map.getWIDTH() && j >= 0) {
            if (map.getMap().get(new Coordinates(i, j)) instanceof Grass) {
                return true;
            }
        }
        i = coordinates.getRow() + 1;
        j = coordinates.getCol();
        if (i < map.getHEIGHT() && i >= 0 && j < map.getWIDTH() && j >= 0) {
            if (map.getMap().get(new Coordinates(i, j)) instanceof Grass) {
                return true;
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() + 1;
        if (i < map.getHEIGHT() && i >= 0 && j < map.getWIDTH() && j >= 0) {
            if (map.getMap().get(new Coordinates(i, j)) instanceof Grass) {
                return true;
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() - 1;
        if (i < map.getHEIGHT() && i >= 0 && j < map.getWIDTH() && j >= 0) {
            return map.getMap().get(new Coordinates(i, j)) instanceof Grass;
        }
        return false;
    }

    @Override
    public void randomMove(Simulation simulation) {
        ArrayList<Coordinates> listOfFreeCoordinates = new ArrayList<>();
        int i = coordinates.getRow() - 1;
        int j = coordinates.getCol();
        if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
            if (simulation.getGameMap().isEmptyCeil(new Coordinates(i, j))) {
                listOfFreeCoordinates.add(new Coordinates(i, j));
            }
        }
        i = coordinates.getRow() + 1;
        j = coordinates.getCol();
        if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
            if (simulation.getGameMap().isEmptyCeil(new Coordinates(i, j))) {
                listOfFreeCoordinates.add(new Coordinates(i, j));
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() + 1;
        if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
            if (simulation.getGameMap().isEmptyCeil(new Coordinates(i, j))) {
                listOfFreeCoordinates.add(new Coordinates(i, j));
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() - 1;
        if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
            if (simulation.getGameMap().isEmptyCeil(new Coordinates(i, j))) {
                listOfFreeCoordinates.add(new Coordinates(i, j));
            }
        }
        if (!listOfFreeCoordinates.isEmpty()){
            simulation.getGameMap().makeEmptyCeil(this.coordinates);
            this.coordinates = listOfFreeCoordinates.get(Simulation.random.nextInt(listOfFreeCoordinates.size()));
            simulation.getGameMap().getMap().put(this.coordinates, this);
        }
    }

    @Override
    public void eat(Simulation simulation) {
        while (true) {
            int i = coordinates.getRow() - 1;
            int j = coordinates.getCol();
            if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
                if (simulation.getGameMap().getMap().get(new Coordinates(i, j)) instanceof Grass) {
                    simulation.getGameMap().makeEmptyCeil(new Coordinates(i, j));
                    break;
                }
            }
            i = coordinates.getRow() + 1;
            j = coordinates.getCol();
            if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
                if (simulation.getGameMap().getMap().get(new Coordinates(i, j)) instanceof Grass) {
                    simulation.getGameMap().makeEmptyCeil(new Coordinates(i, j));
                    break;
                }
            }
            i = coordinates.getRow();
            j = coordinates.getCol() + 1;
            if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
                if (simulation.getGameMap().getMap().get(new Coordinates(i, j)) instanceof Grass) {
                    simulation.getGameMap().makeEmptyCeil(new Coordinates(i, j));
                    break;
                }
            }
            i = coordinates.getRow();
            j = coordinates.getCol() - 1;
            if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
                if (simulation.getGameMap().getMap().get(new Coordinates(i, j)) instanceof Grass) {
                    simulation.getGameMap().makeEmptyCeil(new Coordinates(i, j));
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "H";
    }
}
