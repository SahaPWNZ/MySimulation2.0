package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.Simulation;

import java.util.ArrayList;

public final class Herbivore extends Creature {
    public static ArrayList<Herbivore> entities = new ArrayList<>();

    public Herbivore(Coordinates coordinates) {
        super(1, coordinates);
        entities.add(this);
    }

    @Override
    public void makeMove(Simulation simulation, Coordinates oldCoordinates, Coordinates newCoordinates) {
        this.coordinates = newCoordinates;
        simulation.makeEmptyCeil(oldCoordinates);
        simulation.getMap().put(this.coordinates, this);
    }

    @Override
    public boolean findEat(Simulation simulation) {
        int i = coordinates.getRow() - 1;
        int j = coordinates.getCol();
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.getMap().get(new Coordinates(i, j)) instanceof Grass) {
                return true;
            }
        }
        i = coordinates.getRow() + 1;
        j = coordinates.getCol();
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.getMap().get(new Coordinates(i, j)) instanceof Grass) {
                return true;
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() + 1;
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.getMap().get(new Coordinates(i, j)) instanceof Grass) {
                return true;
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() - 1;
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.getMap().get(new Coordinates(i, j)) instanceof Grass) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void randomMove(Simulation simulation) {
        ArrayList<Coordinates> listOfFreeCoordinates = new ArrayList<>();
        int i = coordinates.getRow() - 1;
        int j = coordinates.getCol();
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.isEmptyCeil(new Coordinates(i, j))) {
                listOfFreeCoordinates.add(new Coordinates(i, j));
            }
        }
        i = coordinates.getRow() + 1;
        j = coordinates.getCol();
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.isEmptyCeil(new Coordinates(i, j))) {
                listOfFreeCoordinates.add(new Coordinates(i, j));
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() + 1;
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.isEmptyCeil(new Coordinates(i, j))) {
                listOfFreeCoordinates.add(new Coordinates(i, j));
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() - 1;
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.isEmptyCeil(new Coordinates(i, j))) {
                listOfFreeCoordinates.add(new Coordinates(i, j));
            }
        }
        if (!listOfFreeCoordinates.isEmpty()){
            simulation.makeEmptyCeil(this.coordinates);
            this.coordinates = listOfFreeCoordinates.get(Simulation.random.nextInt(listOfFreeCoordinates.size()));
            simulation.getMap().put(this.coordinates, this);
        }
    }

    @Override
    public void eat(Simulation simulation) {
        boolean flag = true;
        while (flag) {
            int i = coordinates.getRow() - 1;
            int j = coordinates.getCol();
            if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
                if (simulation.getMap().get(new Coordinates(i, j)) instanceof Grass) {
                    simulation.makeEmptyCeil(new Coordinates(i, j));
                    break;
                }
            }
            i = coordinates.getRow() + 1;
            j = coordinates.getCol();
            if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
                if (simulation.getMap().get(new Coordinates(i, j)) instanceof Grass) {
                    simulation.makeEmptyCeil(new Coordinates(i, j));
                    break;
                }
            }
            i = coordinates.getRow();
            j = coordinates.getCol() + 1;
            if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
                if (simulation.getMap().get(new Coordinates(i, j)) instanceof Grass) {
                    simulation.makeEmptyCeil(new Coordinates(i, j));
                    break;
                }
            }
            i = coordinates.getRow();
            j = coordinates.getCol() - 1;
            if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
                if (simulation.getMap().get(new Coordinates(i, j)) instanceof Grass) {
                    simulation.makeEmptyCeil(new Coordinates(i, j));
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
