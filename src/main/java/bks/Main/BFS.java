package bks.Main;

import bks.Entities.Entity;

import java.util.*;

public class BFS {
    private HashMap<Coordinates, Entity> grid;
    private Coordinates start, end;
    private Queue<Vertex> queue;
    private HashSet<Vertex> set;
    private HashMap<Coordinates, Coordinates> allPath;

    public BFS(HashMap<Coordinates, Entity> grid, Coordinates start, Coordinates end) {
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.queue = new LinkedList<>();
        this.set = new HashSet<>();
        this.allPath = new HashMap<>();
    }

    public void run(GameMap map) {
        Vertex startVertex = new Vertex(start);
        queue.add(startVertex);
        allPath.put(startVertex.coord, null);
        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();
            if (set.add(currentVertex)) {
                if (currentVertex.coord.equals(end)) {
                    System.out.println("Конечная точка достигнута");
                    break;
                }
                for (Coordinates neighborCoord : currentVertex.coord.getNeighbors(map)) {
                    if (!queue.contains(new Vertex(neighborCoord)) && !(allPath.containsKey(neighborCoord))) {
                        queue.add(new Vertex(neighborCoord));
                        allPath.put(neighborCoord, currentVertex.coord);
                    }
                }
            }
        }
        System.out.println("Путь найден");
        List<Coordinates> path = new LinkedList<>();
        Coordinates temp = this.end;
        while (temp != null) {
            path.addFirst(temp);
            temp = allPath.get(temp);
        }
        System.out.println(path);


    }


    class Vertex {
        Coordinates coord;

        public Vertex(Coordinates coord) {
            this.coord = coord;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return Objects.equals(coord, vertex.coord);
        }

        @Override
        public int hashCode() {
            return Objects.hash(coord);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "coord=" + coord +
                    '}';
        }
    }
}

