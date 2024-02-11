package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.Simulation;

import java.util.ArrayList;

public final class Predator extends Creature {

    public static ArrayList<Predator> entities = new ArrayList<>();

    public Predator(Coordinates coordinates) {
        super(2, coordinates);
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
            if (simulation.getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                return true;
            }
        }
        i = coordinates.getRow() + 1;
        j = coordinates.getCol();
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                return true;
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() + 1;
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                return true;
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() - 1;
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                return true;
            }
        }
        return false;
    }

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
        if (!listOfFreeCoordinates.isEmpty()) {
            simulation.makeEmptyCeil(this.coordinates);
            this.coordinates = listOfFreeCoordinates.get(Simulation.random.nextInt(listOfFreeCoordinates.size()));
            simulation.getMap().put(this.coordinates, this);
        }
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public void eat(Simulation simulation) {
        boolean flag = true;
        while (flag) {
            int i = coordinates.getRow() - 1;
            int j = coordinates.getCol();
            if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
                if (simulation.getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                    simulation.makeEmptyCeil(new Coordinates(i, j));
                    for (Herbivore herbivore : Herbivore.entities) {
                        if (herbivore.getCoordinates().equals(new Coordinates(i, j))) {
                            Herbivore.entities.remove(herbivore);
                        }
                    }
                    break;
                }
            }
            i = coordinates.getRow() + 1;
            j = coordinates.getCol();
            if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
                if (simulation.getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                    simulation.makeEmptyCeil(new Coordinates(i, j));
                    for (Herbivore herbivore : Herbivore.entities) {
                        if (herbivore.getCoordinates().equals(new Coordinates(i, j))) {
                            Herbivore.entities.remove(herbivore);
                        }
                    }
                    break;
                }
            }
            i = coordinates.getRow();
            j = coordinates.getCol() + 1;
            if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
                if (simulation.getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                    simulation.makeEmptyCeil(new Coordinates(i, j));
                    for (Herbivore herbivore : Herbivore.entities) {
                        if (herbivore.getCoordinates().equals(new Coordinates(i, j))) {
                            Herbivore.entities.remove(herbivore);
                        }
                    }
                    break;
                }
            }
            i = coordinates.getRow();
            j = coordinates.getCol() - 1;
            if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
                if (simulation.getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                    simulation.makeEmptyCeil(new Coordinates(i, j));
                    for (Herbivore herbivore : Herbivore.entities) {
                        if (herbivore.getCoordinates().equals(new Coordinates(i, j))) {
                            Herbivore.entities.remove(herbivore);
                        }
                    }
                    break;
                }
            }
        }
    }


}
