package bks.Main;

import bks.Entities.Entity;

import java.util.HashMap;
import java.util.Random;

public class GameMap {
    private final HashMap<Coordinates, Entity> map = new HashMap<>(); //структура хранящая координаты занятых сущностями ячеек
    private final int WIDTH;
    private final int HEIGHT;
    public static Random random = new Random();

    public GameMap(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public HashMap<Coordinates, Entity> getMap() {
        return map;
    }

    public void makeEmptyCeil(Coordinates coordinates) {
        this.map.put(coordinates, null); //освобождает ячейку по заданным координатам
    }

    public Coordinates getRandomCoordinates() {
        return new Coordinates(random.nextInt(getHEIGHT()), random.nextInt(getWIDTH()));
    }

    public boolean isEmptyCeil(Coordinates coordinates) {
        return this.map.get(coordinates) == null; //проверка свободна ли ячейка
    }

    public boolean isEmptyCeil(Coordinates coordinates, Coordinates target) { //ещё одна вариация проверки свободной ячейки, применяемая в алгоритме поиска пути
        return this.map.get(coordinates) == null || coordinates.equals(target);
    }
}
