package bks.Main;

import bks.Entities.Creature;
import bks.Entities.Entity;
import bks.Entities.Herbivore;

import java.util.Map;
import java.util.Random;

public class Test {
    static Random random = new Random();

    public static void initTest() {
        GameMap gameMap = new GameMap(30, 15);

        int valueOfCreates = 8;
        while (valueOfCreates > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(gameMap.getWidth()), random.nextInt(gameMap.getHeight()));
            if (gameMap.isEmpty(coordinates)) {
                gameMap.map.put(coordinates, new Herbivore(coordinates));
                valueOfCreates--;
            }
        }
        for (Map.Entry<Coordinates, Entity> entry : gameMap.map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Render.mapRender(gameMap);

    }
}
