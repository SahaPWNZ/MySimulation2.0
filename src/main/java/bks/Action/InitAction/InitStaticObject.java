package bks.Action.InitAction;

import bks.Entities.Rock;
import bks.Entities.Tree;
import bks.Main.Coordinates;
import bks.Main.Simulation;

public class InitStaticObject extends InitAction {
    private int staticObject = 4;
    @Override
    public void makeAction(Simulation simulation) {
        while (staticObject > 0) {
            Coordinates coordinatesRock = new Coordinates(random.nextInt(simulation.getGameMap().getHEIGHT()), random.nextInt(simulation.getGameMap().getWIDTH()));
            if (simulation.getGameMap().isEmptyCeil(coordinatesRock)) {
                simulation.getGameMap().getMap().put(coordinatesRock, new Rock(coordinatesRock));
                staticObject--;
            }
            Coordinates coordinatesTree = new Coordinates(random.nextInt(simulation.getGameMap().getHEIGHT()), random.nextInt(simulation.getGameMap().getWIDTH()));
            if (simulation.getGameMap().isEmptyCeil(coordinatesTree)) {
                simulation.getGameMap().getMap().put(coordinatesTree, new Tree(coordinatesTree));
                staticObject--;
            }
        }
    }
}
