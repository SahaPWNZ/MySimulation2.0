package bks.Main;

import bks.Entities.Entity;
import bks.Entities.Grass;
import bks.Entities.Herbivore;

import java.util.Map;
import java.util.Random;

public class Test {
    static Random random = new Random();

    public static void initTest() {
        GameMap gameMap = new GameMap(5, 10);

        int valueOfCreates = 5;
        while (valueOfCreates > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(gameMap.getWidth()), random.nextInt(gameMap.getHeight()));
            if (gameMap.isEmptyCeil(coordinates)) {
                gameMap.getMap().put(coordinates, new Herbivore(coordinates));
                valueOfCreates--;
            }
        }
        int valueOfGrass = 3;
        while(valueOfGrass > 0){
            Coordinates coordinates = new Coordinates(random.nextInt(gameMap.getWidth()), random.nextInt(gameMap.getHeight()));
            if (gameMap.isEmptyCeil(coordinates)) {
                gameMap.getMap().put(coordinates, new Grass(coordinates));
                valueOfGrass--;
            }
        }

        for (Map.Entry<Coordinates, Entity> entry : gameMap.getMap().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Render.mapRender(gameMap);

    }
}
