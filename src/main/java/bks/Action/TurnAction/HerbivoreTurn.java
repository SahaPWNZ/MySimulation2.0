package bks.Action.TurnAction;

import bks.Entities.Creature;
import bks.Entities.Entity;
import bks.Entities.Grass;
import bks.Entities.Herbivore;
import bks.Main.BFS;
import bks.Main.Coordinates;
import bks.Main.Simulation;

import java.util.ArrayList;

public final class HerbivoreTurn extends TurnAction {
    @Override
    public void makeTurnAction(Simulation simulation) {
        for (Herbivore herbivore : Herbivore.entities) {
            if (herbivore.findEat(simulation.getGameMap(), Grass.class)) {
                herbivore.eat(simulation.getGameMap());
            } else {
                makeMoveToEat(herbivore, simulation);
            }
        }
    }

    private void makeMoveToEat(Herbivore herbivore, Simulation simulation) {
        ArrayList<Entity> listOfEat = Creature.getListOfEats(Grass.class, simulation.getGameMap());
        if (!listOfEat.isEmpty()) {
            BFS bfs = new BFS(herbivore.getCoordinates(), herbivore.getCoordinatesOfClosestEat(simulation.getGameMap(), listOfEat));
            Coordinates turnCoordinates = bfs.run(simulation);
            if (turnCoordinates != null) {
                herbivore.makeMove(simulation.getGameMap(), herbivore.getCoordinates(), turnCoordinates);
            } else {
                herbivore.randomMove(simulation.getGameMap());
            }
        } else {
            herbivore.randomMove(simulation.getGameMap());
        }
    }
}
