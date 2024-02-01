package bks.Main;

import bks.Entities.Entity;

import java.util.HashMap;

public class Simulation {
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
        return !this.map.containsKey(coordinates);
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

    public void startSimulation() {
    }

    public void pauseSimulation() {
    }

}
