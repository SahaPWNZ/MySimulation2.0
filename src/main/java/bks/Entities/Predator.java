package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.GameMap;
import bks.Main.Simulation;

import java.util.ArrayList;

public final class Predator extends Creature {

    public static ArrayList<Predator> entities = new ArrayList<>(); //лист хищников

    public Predator(Coordinates coordinates) {
        super(2, coordinates);
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
            if (map.getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                return true;
            }
        }
        i = coordinates.getRow() + 1;
        j = coordinates.getCol();
        if (i < map.getHEIGHT() && i >= 0 && j < map.getWIDTH() && j >= 0) {
            if (map.getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                return true;
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() + 1;
        if (i < map.getHEIGHT() && i >= 0 && j < map.getWIDTH() && j >= 0) {
            if (map.getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                return true;
            }
        }
        i = coordinates.getRow();
        j = coordinates.getCol() - 1;
        if (i < map.getHEIGHT() && i >= 0 && j < map.getWIDTH() && j >= 0) {
            return map.getMap().get(new Coordinates(i, j)) instanceof Herbivore;
        }
        return false;
    }

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
        if (!listOfFreeCoordinates.isEmpty()) {
            simulation.getGameMap().makeEmptyCeil(this.coordinates);
            this.coordinates = listOfFreeCoordinates.get(Simulation.random.nextInt(listOfFreeCoordinates.size()));
            simulation.getGameMap().getMap().put(this.coordinates, this);
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
            if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
                if (simulation.getGameMap().getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                    simulation.getGameMap().makeEmptyCeil(new Coordinates(i, j));
                    Herbivore removeHerb = null;
                    for (Herbivore herbivore : Herbivore.entities) {
                        if (herbivore.getCoordinates().equals(new Coordinates(i, j))) {
                            removeHerb = herbivore;
                        }
                    }
                    if (removeHerb != null) {
                        Herbivore.entities.remove(removeHerb);
                        flag = false;
                    }
                }
            }
            i = coordinates.getRow() + 1;
            j = coordinates.getCol();
            if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
                if (simulation.getGameMap().getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                    simulation.getGameMap().makeEmptyCeil(new Coordinates(i, j));
                    Herbivore removeHerb = null;
                    for (Herbivore herbivore : Herbivore.entities) {
                        if (herbivore.getCoordinates().equals(new Coordinates(i, j))) {
                            removeHerb = herbivore;
                        }
                    }
                    if (removeHerb != null) {
                        Herbivore.entities.remove(removeHerb);
                        flag = false;
                    }
                }
            }
            i = coordinates.getRow();
            j = coordinates.getCol() + 1;
            if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
                if (simulation.getGameMap().getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                    simulation.getGameMap().makeEmptyCeil(new Coordinates(i, j));
                    Herbivore removeHerb = null;
                    for (Herbivore herbivore : Herbivore.entities) {
                        if (herbivore.getCoordinates().equals(new Coordinates(i, j))) {
                            removeHerb = herbivore;
                        }
                    }
                    if (removeHerb != null) {
                        Herbivore.entities.remove(removeHerb);
                        flag = false;
                    }
                }
            }
            i = coordinates.getRow();
            j = coordinates.getCol() - 1;
            if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
                if (simulation.getGameMap().getMap().get(new Coordinates(i, j)) instanceof Herbivore) {
                    simulation.getGameMap().makeEmptyCeil(new Coordinates(i, j));
                    Herbivore removeHerb = null;
                    for (Herbivore herbivore : Herbivore.entities) {
                        if (herbivore.getCoordinates().equals(new Coordinates(i, j))) {
                            removeHerb = herbivore;
                        }
                    }
                    if (removeHerb != null) {
                        Herbivore.entities.remove(removeHerb);
                        flag = false;
                    }
                }
            }
        }
    }


}
