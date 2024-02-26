package bks.Action.InitAction;

import bks.Entities.Grass;
import bks.Main.Coordinates;
import bks.Main.GameMap;
import bks.Main.Simulation;

public class InitGrassSpawn extends InitAction {
    private  int countOfGrass = 3;
    @Override
    public void makeAction(GameMap map) {
       while (countOfGrass > 0) {
            Coordinates coordinatesGrass = new Coordinates(random.nextInt(map.getHEIGHT()), random.nextInt(map.getWIDTH()));
            if (map.isEmptyCeil(coordinatesGrass)) {
                map.getMap().put(coordinatesGrass, new Grass(coordinatesGrass));
                countOfGrass--;
            }
        }
    }
}
