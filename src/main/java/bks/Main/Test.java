package bks.Main;

import bks.Entities.Entity;
import bks.Entities.Grass;
import bks.Entities.Herbivore;

import java.util.Map;
import java.util.Random;

public class Test {
    static Random random = new Random();

    public static void initTest() {
        Simulation simulation = new Simulation(4, 4);

        int valueOfCreates = 1;
        while (valueOfCreates > 0) {
//            Coordinates coordinates = new Coordinates(random.nextInt(gameMap.getHeight()), random.nextInt(gameMap.getWidth()));
            Coordinates coordinates = new Coordinates(1,1);
//            System.out.println(coordinates.getNeighbors(gameMap));
            if (simulation.isEmptyCeil(coordinates)) {
                simulation.getMap().put(coordinates, new Herbivore(coordinates));
                valueOfCreates--;
            }
        }
        int valueOfGrass = 1;
        while(valueOfGrass > 0){
////            Coordinates coordinates = new Coordinates(random.nextInt(gameMap.getHeight()), random.nextInt(gameMap.getWidth()));
            Coordinates coordinates = new Coordinates(2,2);
            if (simulation.isEmptyCeil(coordinates)) {
                simulation.getMap().put(coordinates, new Grass(coordinates));
                valueOfGrass--;
            }
        }

        for (Map.Entry<Coordinates, Entity> entry : simulation.getMap().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        simulation.mapRender();
        BFS bfs = new BFS(simulation.getMap(), new Coordinates(1,1), new Coordinates(2,2));
        bfs.run(simulation);
//        System.out.println(Herbivore.entities.get(0).findEat(simulation));

//        if (Herbivore.entities.get(0).findEat(simulation)){
//
//        }
    }
}
