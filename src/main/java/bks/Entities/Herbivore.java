package bks.Entities;

import bks.Main.Coordinates;
import bks.Main.Simulation;

import java.util.ArrayList;

public final class Herbivore extends Creature {
//    public static ArrayList<Herbivore> entities = new ArrayList<Herbivore>();
    public Herbivore(Coordinates coordinates) {
        super(1, 10, coordinates);
//        entities.add(this);
    }

    @Override
    void makeMove() {

        //если еда - то кушаем и не ходим дальше,
        //запуск алгоритма поиска пути и сравнение с листом объектов целей
        //когда ячейка==цель, то возвращаем путь
        //двигаемся цели на одну клетку
        //проверка на еду в соседних клетках
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
    public String toString() {
        return "H";
    }
}
