package bks.Entities;

import bks.Main.Coordinates;

public final class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates) {

        super(1, 10, coordinates);
    }

    @Override
    void makeMove() {
            //проверка соседей на ячейки с едой
                //если еда - то кушаем и не ходим дальше,
            //запуск алгоритма поиска пути и сравнение с листом объектов целей
            //когда ячейка==цель, то возвращаем путь
            //двигаемся цели на одну клетку
                //проверка на еду в соседних клетках
    }

    @Override
    public String toString() {
        return "H";
    }
}
