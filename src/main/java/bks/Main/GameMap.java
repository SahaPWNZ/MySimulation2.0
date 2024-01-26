package bks.Main;

import bks.Entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    HashMap<Coordinates, Entity> map = new HashMap<>();
    private final int width;
    private final int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isEmpty(Coordinates coordinates) {
        return !this.map.containsKey(coordinates);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void printAllEntity(){
        for (Map.Entry<Coordinates, Entity> entry: this.map.entrySet() ) {

        }
    }

}
