package bks.Action.InitAction;

import bks.Action.Action;
import bks.Entities.Predator;
import bks.Main.Coordinates;
import bks.Main.Simulation;

public class InitPredatorsSpawn extends InitAction {
    private int countOfPredator = 1;
    @Override
   public void makeAction(Simulation simulation) {
        while (countOfPredator > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(simulation.getGameMap().getHEIGHT()), random.nextInt(simulation.getGameMap().getWIDTH()));
            if (simulation.getGameMap().isEmptyCeil(coordinates)) {
                simulation.getGameMap().getMap().put(coordinates, new Predator(coordinates));
                countOfPredator--;
            }
        }
    }
}
