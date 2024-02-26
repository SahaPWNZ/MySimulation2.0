package bks.Action.InitAction;

import bks.Entities.Predator;
import bks.Main.Coordinates;
import bks.Main.GameMap;

public class InitPredatorsSpawn extends InitAction {
    private int countOfPredator = 1;
    @Override
   public void makeAction(GameMap map) {
        while (countOfPredator > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(map.getHEIGHT()), random.nextInt(map.getWIDTH()));
            if (map.isEmptyCeil(coordinates)) {
                map.getMap().put(coordinates, new Predator(coordinates));
                countOfPredator--;
            }
        }
    }
}
