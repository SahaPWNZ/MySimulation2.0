package bks.Main;

import bks.Entities.Entity;

import java.util.*;

public class BFS {
    private HashMap<Coordinates, Entity> grid;
    private Coordinates start, end;
    private Queue<Vertex> queue;
    private HashSet<Vertex> set;

    public BFS(HashMap<Coordinates, Entity> grid, Coordinates start, Coordinates end) {
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.queue = new LinkedList<>();
        this.set = new HashSet<>();

    }

    public void run(GameMap map) {
        Vertex startVertex = new Vertex(start);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();
            if (set.add(currentVertex)) {
                System.out.println(currentVertex);
                if (currentVertex.coord.equals(end)) {
                    System.out.println("Конечная точка достигнута");
                    break;
                }
                for (Coordinates neighborCoord : currentVertex.coord.getNeighbors(map)) {
                    if (!queue.contains(new Vertex(neighborCoord))) {
                        queue.add(new Vertex(neighborCoord));
                    }
                }
            }
//            for (Coordinates neighborCoord : currentVertex.coord.getNeighbors(map)) {
//                if (!queue.contains(new Vertex(neighborCoord)) {
//                    queue.add(new Vertex(neighborCoord, currentVertex));
//                }
//            }
        }

        if (!queue.isEmpty()) {
            System.out.println("Нет пути");
        } else {
            System.out.println("Путь найден");
            printPath(startVertex);
        }
    }

    private void printPath(Vertex startVertex) {


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

