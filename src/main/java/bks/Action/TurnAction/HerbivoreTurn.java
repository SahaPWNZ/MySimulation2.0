package bks.Action.TurnAction;

import bks.Entities.Creature;
import bks.Entities.Entity;
import bks.Entities.Grass;
import bks.Entities.Herbivore;
import bks.Main.BFS;
import bks.Main.Coordinates;
import bks.Main.Simulation;

import java.util.ArrayList;

public class HerbivoreTurn extends TurnAction{
    @Override
    public void makeTurnAction(Simulation simulation) {
        for (Herbivore herbivore : Herbivore.entities) {
            if (herbivore.findEat(simulation)) {
                herbivore.eat(simulation);
            } else {
                ArrayList<Entity> listOfEat = Creature.getListOfEats(Grass.class, simulation);
                if (!listOfEat.isEmpty()) {
                    BFS bfs = new BFS(herbivore.getCoordinates(), herbivore.getCoordinatesOfClosestEat(simulation, listOfEat));
                    Coordinates turnCoordinates = bfs.run(simulation);
                    if (turnCoordinates != null) {
                        herbivore.makeMove(simulation, herbivore.getCoordinates(), turnCoordinates);
                    } else {
                        herbivore.randomMove(simulation);
                    }
                } else {
                    herbivore.randomMove(simulation);
                }
            }
        }
    }
}
