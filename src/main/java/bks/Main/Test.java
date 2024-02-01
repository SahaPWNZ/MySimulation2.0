package bks.Main;

import bks.Entities.Entity;
import bks.Entities.Grass;
import bks.Entities.Herbivore;

import java.util.Map;
import java.util.Random;

public class Test {
    static Random random = new Random();

    public static void initTest() {
        GameMap gameMap = new GameMap(4, 4);

        int valueOfCreates = 1;
        while (valueOfCreates > 0) {
//            Coordinates coordinates = new Coordinates(random.nextInt(gameMap.getHeight()), random.nextInt(gameMap.getWidth()));
            Coordinates coordinates = new Coordinates(1,1);
//            System.out.println(coordinates.getNeighbors(gameMap));
            if (gameMap.isEmptyCeil(coordinates)) {
                gameMap.getMap().put(coordinates, new Herbivore(coordinates));
                valueOfCreates--;
            }
        }
        int valueOfGrass = 1;
        while(valueOfGrass > 0){
////            Coordinates coordinates = new Coordinates(random.nextInt(gameMap.getHeight()), random.nextInt(gameMap.getWidth()));
            Coordinates coordinates = new Coordinates(2,1);
            if (gameMap.isEmptyCeil(coordinates)) {
                gameMap.getMap().put(coordinates, new Grass(coordinates));
                valueOfGrass--;
            }
        }

        for (Map.Entry<Coordinates, Entity> entry : gameMap.getMap().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Render.mapRender(gameMap);
        BFS bfs = new BFS(gameMap.getMap(), new Coordinates(1,1), new Coordinates(3,3));
        bfs.run(gameMap);


    }
}
