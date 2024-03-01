package bks.Action.InitAction;


import bks.Entities.Entity;
import bks.Entities.Herbivore;
import bks.Main.Coordinates;
import bks.Main.GameMap;
import bks.Main.Simulation;

import java.util.HashMap;

public class initHerbivores extends InitAction {

    private int countHerbivores = 2;
    @Override
   public void makeAction(GameMap map) {
        while (countHerbivores > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(map.getHEIGHT()), random.nextInt(map.getWIDTH()));
            if (map.isEmptyCeil(coordinates)) {
                map.getMap().put(coordinates, new Herbivore(coordinates));
                countHerbivores--;
            }
        }
    }
}
