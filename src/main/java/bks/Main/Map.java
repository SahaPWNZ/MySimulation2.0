package bks.Main;

import bks.Entities.Entity;

import java.util.HashMap;

public class Map {
    private HashMap<Coordinates, Entity> map = new HashMap<>();
    private final int width;
    private final int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
