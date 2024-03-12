package bks.Action.TurnAction;

import bks.Entities.*;
import bks.Main.BFS;
import bks.Main.Coordinates;
import bks.Main.Simulation;

import java.util.ArrayList;

public final class PredatorTurn extends TurnAction{
    @Override
    public void makeTurnAction(Simulation simulation) {
        for (Predator predator : Predator.entities) {
            if (predator.findEat(simulation.getGameMap(), Herbivore.class)) {
                predator.eat(simulation.getGameMap());
            } else {
                makeMoveToEat(predator, simulation);
            }
        }
    }
    private void makeMoveToEat (Predator predator, Simulation simulation){
        ArrayList<Entity> listOfEat = Creature.getListOfEats(Herbivore.class, simulation.getGameMap());
        if (!listOfEat.isEmpty()) {
            BFS bfs = new BFS(predator.getCoordinates(), predator.getCoordinatesOfClosestEat(simulation.getGameMap(), listOfEat));
            Coordinates turnCoordinates = bfs.run(simulation);
            if (turnCoordinates != null) {
                predator.makeMove(simulation.getGameMap(), predator.getCoordinates(), turnCoordinates);
            } else {
                predator.randomMove(simulation.getGameMap());
            }
        } else {
            predator.randomMove(simulation.getGameMap());
        }
    }
}
