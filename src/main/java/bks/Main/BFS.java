package bks.Main;

import bks.Entities.Entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private HashMap<Coordinates, Entity> grid;
    private Coordinates start, end;
    private Queue<Vertex> queue;

    public BFS(HashMap<Coordinates, Entity> grid, Coordinates start, Coordinates end) {
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.queue = new LinkedList<>();
    }

    public void run() {
        Vertex startVertex = new Vertex(start, grid.get(start));
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();

            if (currentVertex.coord.equals(end)) {
                System.out.println("Конечная точка достигнута");
                break;
            }
            for (Coordinates neighborCoord : currentVertex.coord.getNeighbors()) {
                if (!queue.contains(new Vertex(neighborCoord, grid.get(neighborCoord)))) {
                    queue.add(new Vertex(neighborCoord, grid.get(neighborCoord)));
                }
            }
        }

        if (!queue.isEmpty()) {
            System.out.println("Нет пути");
        } else {
            System.out.println("Путь найден");
            printPath(startVertex);
        }
    }

    private void printPath(Vertex startVertex) {
        if (startVertex == null) {
            return;
        }

        printPath(startVertex.parent);
        System.out.println(startVertex.coord);
    }


    class Vertex {
        Coordinates coord;
        Entity state;
        Vertex parent;

        public Vertex(Coordinates coord, Entity state) {
            this.coord = coord;
            this.state = state;
        }
    }
}

