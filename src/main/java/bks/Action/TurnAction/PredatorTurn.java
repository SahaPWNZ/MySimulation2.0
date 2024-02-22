package bks.Action.TurnAction;

import bks.Entities.*;
import bks.Main.BFS;
import bks.Main.Coordinates;
import bks.Main.Simulation;

import java.util.ArrayList;

public class PredatorTurn extends TurnAction{
    @Override
    public void makeTurnAction(Simulation simulation) {
        for (Predator predator : Predator.entities) {
            if (predator.findEat(simulation.getGameMap())) {
                predator.eat(simulation);
            } else {
                ArrayList<Entity> listOfEat = Creature.getListOfEats(Herbivore.class, simulation);
                if (!listOfEat.isEmpty()) {
                    BFS bfs = new BFS(predator.getCoordinates(), predator.getCoordinatesOfClosestEat(simulation, listOfEat));
                    Coordinates turnCoordinates = bfs.run(simulation);
                    if (turnCoordinates != null) {
                        predator.makeMove(simulation.getGameMap(), predator.getCoordinates(), turnCoordinates);
                    } else {
                        predator.randomMove(simulation);
                    }
                } else {
                    predator.randomMove(simulation);
                }
            }
        }
    }
}
