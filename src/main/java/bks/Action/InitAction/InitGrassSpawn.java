package bks.Action.InitAction;

import bks.Action.Action;
import bks.Entities.Grass;
import bks.Main.Coordinates;
import bks.Main.Simulation;

public class InitGrassSpawn extends InitAction {
    private  int countOfGrass = 3;
    @Override
    public void makeAction(Simulation simulation) {
       while (countOfGrass > 0) {
            Coordinates coordinatesGrass = new Coordinates(random.nextInt(simulation.getGameMap().getHEIGHT()), random.nextInt(simulation.getGameMap().getWIDTH()));
            if (simulation.getGameMap().isEmptyCeil(coordinatesGrass)) {
                simulation.getGameMap().getMap().put(coordinatesGrass, new Grass(coordinatesGrass));
                countOfGrass--;
            }
        }
    }
}
