package bks.Action.TurnAction;

import bks.Entities.*;
import bks.Main.BFS;
import bks.Main.Coordinates;
import bks.Main.Simulation;

import java.util.ArrayList;

public final class PredatorTurn extends TurnAction{
    @Override
    public void makeTurnAction(Simulation simulation) { //весь ход сущности
        ArrayList<Entity> listPredator= Creature.getListOfEntity(Predator.class, simulation.getGameMap());
        for (Entity entity : listPredator) {
            Predator predator = (Predator) entity;
            if (predator.isEatInNeighbors(simulation.getGameMap(), Herbivore.class)) {
                predator.eat(simulation.getGameMap());
            } else {
                makeMoveToEat(predator, simulation);
            }
        }
    }
    private void makeMoveToEat (Predator predator, Simulation simulation){ //сущность делает шаг в сторону пищи
        ArrayList<Entity> listOfEat = Creature.getListOfEntity(Herbivore.class, simulation.getGameMap());
        if (!listOfEat.isEmpty()) {
            BFS bfs = new BFS(predator.getCoordinates(), predator.getCoordinatesOfClosestEat(simulation.getGameMap(), listOfEat));
            Coordinates turnCoordinates = bfs.runBFS(simulation);
            if (turnCoordinates != null) {
                predator.makeMove(simulation.getGameMap(), predator.getCoordinates(), turnCoordinates);
            } else {
                predator.makeRandomMove(simulation.getGameMap());
            }
        } else {
            predator.makeRandomMove(simulation.getGameMap());
        }
    }
}
