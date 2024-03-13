package bks.Action.TurnAction;

import bks.Entities.*;
import bks.Main.BFS;
import bks.Main.Coordinates;
import bks.Main.GameMap;

import java.util.ArrayList;

public final class PredatorTurn extends TurnAction{
    @Override
    public void makeTurnAction(GameMap map) { //весь ход сущности
        ArrayList<Entity> listPredator= Creature.getListOfEntity(Predator.class, map);
        for (Entity entity : listPredator) {
            Predator predator = (Predator) entity;
            if (predator.isEatInNeighbors(map, Herbivore.class)) {
                predator.eat(map, Herbivore.class);
            } else {
                makeMoveToEat(predator, map);
            }
        }
    }
    private void makeMoveToEat (Predator predator, GameMap map){ //сущность делает шаг в сторону пищи
        ArrayList<Entity> listOfEat = Creature.getListOfEntity(Herbivore.class, map);
        if (!listOfEat.isEmpty()) {
            BFS bfs = new BFS(predator.getCoordinates(), predator.getCoordinatesOfClosestEat(map, listOfEat));
            Coordinates turnCoordinates = bfs.runBFS(map);
            if (turnCoordinates != null) {
                predator.makeMove(map, predator.getCoordinates(), turnCoordinates);
            } else {
                predator.makeRandomMove(map);
            }
        } else {
            predator.makeRandomMove(map);
        }
    }
}
