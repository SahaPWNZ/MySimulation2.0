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

    public Coordinates run(Simulation simulation) {
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

    public ArrayList<Coordinates> getNeighbors2(Simulation simulation, Coordinates currentCoordinates) {
        ArrayList<Coordinates> listOfNeighbors = new ArrayList<>();
        int i = currentCoordinates.getRow() - 1;
        int j = currentCoordinates.getCol();
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.isEmptyCeil(new Coordinates(i, j), end)) {
                listOfNeighbors.add(new Coordinates(i, j));
            }
        }
        i = currentCoordinates.getRow() + 1;
        j = currentCoordinates.getCol();
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.isEmptyCeil(new Coordinates(i, j), end)) {
                listOfNeighbors.add(new Coordinates(i, j));
            }
        }
        i = currentCoordinates.getRow();
        j = currentCoordinates.getCol() + 1;
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.isEmptyCeil(new Coordinates(i, j), end)) {
                listOfNeighbors.add(new Coordinates(i, j));
            }
        }
        i = currentCoordinates.getRow();
        j = currentCoordinates.getCol() - 1;
        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
            if (simulation.isEmptyCeil(new Coordinates(i, j), end)) {
                listOfNeighbors.add(new Coordinates(i, j));
            }
        }
        return listOfNeighbors;
    }

}

