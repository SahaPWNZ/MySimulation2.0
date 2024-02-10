package bks.Main;

import bks.Entities.Entity;
import bks.Entities.Grass;
import bks.Entities.Herbivore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Simulation {
    public static Random random = new Random();
    private int countTurn = 0;
    private HashMap<Coordinates, Entity> map = new HashMap<>();
    private final int WIDTH;
    private final int HEIGHT;


    public Simulation(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    public HashMap<Coordinates, Entity> getMap() {
        return map;
    }

    public boolean isEmptyCeil(Coordinates coordinates) {
        return this.map.get(coordinates) == null;
    }

    public void makeEmptyCeil(Coordinates coordinates) {
        this.map.put(coordinates, null);
    }

    public boolean isEmptyCeil(Coordinates coordinates, Coordinates target) {
        return this.map.get(coordinates) == null || coordinates.equals(target);
    }


    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }


    public void mapRender() {
        for (int i = 0; i < this.getHEIGHT(); i++) {
            for (int j = 0; j < this.getWIDTH(); j++) {
                if (this.isEmptyCeil(new Coordinates(i, j))) {
                    System.out.print("O" + " ");
                } else {
                    System.out.print(this.getMap().get(new Coordinates(i, j)) + " ");
                }
            }
            System.out.println();
        }

    }

    public void nextTurn() {
    }

    public ArrayList<Entity> getListOfEats(Class<?> eat) {
        ArrayList<Entity> result = new ArrayList<>();
        for (Map.Entry<Coordinates, Entity> entry : this.getMap().entrySet()) {
            if (eat.isInstance(entry.getValue())) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public Coordinates getCoordinatesOfClosestEat(Coordinates selfCoordinate, ArrayList<Entity> listOfEat) {
        int selfVectorCoordinate = selfCoordinate.getCol() + selfCoordinate.getRow();
        int minEatVector = getHEIGHT() + getWIDTH() + 2;
        Coordinates result = null;
        for (Entity entity : listOfEat) {
            int eatVectorCoordinate = entity.getCoordinates().getCol() + entity.getCoordinates().getRow();
            if (minEatVector > Math.abs(eatVectorCoordinate - selfVectorCoordinate)){
                minEatVector = Math.abs(eatVectorCoordinate - selfVectorCoordinate);
                result = entity.getCoordinates();
            }
        }
        return result;
    }

    public void nextTurnHerbivore(ArrayList<Herbivore> herbivores) {
        for (Herbivore herbivore : herbivores) {
            if (herbivore.findEat(this)) {
                herbivore.eat(this);
            } else {
                ArrayList<Entity> listOfEat = getListOfEats(Grass.class);
                if (!listOfEat.isEmpty()){
                    BFS bfs = new BFS(herbivore.getCoordinates(), getCoordinatesOfClosestEat(herbivore.getCoordinates(), listOfEat));
                    Coordinates turnCoordinates = bfs.run(this);
                    if (turnCoordinates !=null) {
                        herbivore.makeMove(this, herbivore.getCoordinates(), turnCoordinates);
                    }
                    else {
                        herbivore.randomMove(this);
                    }
                }
                else {
                    herbivore.randomMove(this);
                }
            }
        }
    }

    public void startSimulation() {
    }

    public void pauseSimulation() {
    }

}
