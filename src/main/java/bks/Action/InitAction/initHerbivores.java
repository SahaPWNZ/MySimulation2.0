package bks.Action.InitAction;


import bks.Entities.Herbivore;
import bks.Main.Coordinates;
import bks.Main.Simulation;

public class initHerbivores extends InitAction {
    private int countHerbivores = 2;
    @Override
   public void makeAction(Simulation simulation) {
        while (countHerbivores > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(simulation.getGameMap().getHEIGHT()), random.nextInt(simulation.getGameMap().getWIDTH()));
            if (simulation.getGameMap().isEmptyCeil(coordinates)) {
                simulation.getGameMap().getMap().put(coordinates, new Herbivore(coordinates));
                countHerbivores--;
            }
        }
    }
}
