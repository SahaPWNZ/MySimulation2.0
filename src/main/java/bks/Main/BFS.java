package bks.Main;

import java.util.*;

public class BFS { //класс для реализации алгоритма поиска пути
    private final Coordinates start;
    private final Coordinates end;
    private final Queue<Coordinates> queue;
    private final HashSet<Coordinates> set;
    private final HashMap<Coordinates, Coordinates> allPath;

    public BFS(Coordinates start, Coordinates end) {
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
                for (Coordinates neighborCoord : getNeighbors2(simulation.getGameMap(), currentCoordinates)) {
                    if (!queue.contains(neighborCoord) && !(allPath.containsKey(neighborCoord))) {
                        queue.add(neighborCoord);
                        allPath.put(neighborCoord, currentCoordinates);
                    }
                }
            }
        }
        return null;
    }

    private ArrayList<Coordinates> getNeighbors2(GameMap map, Coordinates coordinates) { //алгоримт поиска соседей под поиск пути
        ArrayList<Coordinates> listOfNeighbors = new ArrayList<>();
        int[][] array = coordinates.getArrayOfCoordinatesNeighbors();
        for (int[] pairOfCoord : array) {
            if (Coordinates.validCoordinates(new Coordinates(pairOfCoord[0], pairOfCoord[1]), map)
                    && map.isEmptyCeil(new Coordinates(pairOfCoord[0], pairOfCoord[1]), end)) {
                listOfNeighbors.add(new Coordinates(pairOfCoord[0], pairOfCoord[1]));
            }
        }
        return listOfNeighbors;
    }

}

