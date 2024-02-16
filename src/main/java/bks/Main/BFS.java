package bks.Main;

import java.util.*;

public class BFS { //класс для реализации алгоритма поиска пути
    private final Coordinates start;
    private final Coordinates end;
    private final Queue<Coordinates> queue;
    private final HashSet<Coordinates> set;
    private final HashMap<Coordinates, Coordinates> allPath;

    public BFS( Coordinates start, Coordinates end) {
        this.start = start;
        this.end = end;
        this.queue = new LinkedList<>();
        this.set = new HashSet<>();
        this.allPath = new HashMap<>();
    }

    public Coordinates run(Simulation simulation) { //метод для запуска поиска пути и возврата координаты первого шага к цели
        queue.add(start);
        allPath.put(start, null);
        while (!queue.isEmpty()) {
            Coordinates currentCoordinates = queue.poll();
            if (set.add(currentCoordinates)) {
                if (currentCoordinates.equals(end)) {
                    LinkedList<Coordinates> path = new LinkedList<>();
                    Coordinates temp = this.end;
                    while (temp != null) {
                        path.addFirst(temp);
                        temp = allPath.get(temp);
                    }
                    return path.get(1);
                }
                for (Coordinates neighborCoord : getNeighbors2(simulation, currentCoordinates)) {
                    if (!queue.contains(neighborCoord) && !(allPath.containsKey(neighborCoord))) {
                        queue.add(neighborCoord);
                        allPath.put(neighborCoord, currentCoordinates);
                    }
                }
            }
        }
        return null;
    }

    private ArrayList<Coordinates> getNeighbors2(Simulation simulation, Coordinates currentCoordinates) { //алгоримт поиска соседей под поиск пути
        ArrayList<Coordinates> listOfNeighbors = new ArrayList<>();
        int i = currentCoordinates.getRow() - 1;
        int j = currentCoordinates.getCol();
        if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
            if (simulation.getGameMap().isEmptyCeil(new Coordinates(i, j), end)) {
                listOfNeighbors.add(new Coordinates(i, j));
            }
        }
        i = currentCoordinates.getRow() + 1;
        j = currentCoordinates.getCol();
        if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
            if (simulation.getGameMap().isEmptyCeil(new Coordinates(i, j), end)) {
                listOfNeighbors.add(new Coordinates(i, j));
            }
        }
        i = currentCoordinates.getRow();
        j = currentCoordinates.getCol() + 1;
        if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
            if (simulation.getGameMap().isEmptyCeil(new Coordinates(i, j), end)) {
                listOfNeighbors.add(new Coordinates(i, j));
            }
        }
        i = currentCoordinates.getRow();
        j = currentCoordinates.getCol() - 1;
        if (i < simulation.getGameMap().getHEIGHT() && i >= 0 && j < simulation.getGameMap().getWIDTH() && j >= 0) {
            if (simulation.getGameMap().isEmptyCeil(new Coordinates(i, j), end)) {
                listOfNeighbors.add(new Coordinates(i, j));
            }
        }
        return listOfNeighbors;
    }

}

