package bks.Main;

import bks.Entities.Entity;

import java.util.*;

public class BFS {
    private HashMap<Coordinates, Entity> grid;
    private Coordinates start, end;
    private Queue<Coordinates> queue;
    private HashSet<Coordinates> set;
    private HashMap<Coordinates, Coordinates> allPath;

    public BFS(HashMap<Coordinates, Entity> grid, Coordinates start, Coordinates end) {
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.queue = new LinkedList<>();
        this.set = new HashSet<>();
        this.allPath = new HashMap<>();
    }

    public void run(Simulation simulation) {
        queue.add(start);
        allPath.put(start, null);
        while (!queue.isEmpty()) {
            Coordinates currentCoordinates = queue.poll();
            if (set.add(currentCoordinates)) {
                if (currentCoordinates.equals(end)) {
                    System.out.println("Конечная точка достигнута");
                    LinkedList<Coordinates> path = new LinkedList<>();
                    Coordinates temp = this.end;
                    while (temp != null) {
                        path.addFirst(temp);
                        temp = allPath.get(temp);
                    }
                    System.out.println(path);
                    break;
                }
                for (Coordinates neighborCoord : currentCoordinates.getNeighbors(simulation)) {
                    if (!queue.contains(neighborCoord) && !(allPath.containsKey(neighborCoord))) {
                        queue.add(neighborCoord);
                        allPath.put(neighborCoord, currentCoordinates);
                    }
                }
            }
        }


    }
}

