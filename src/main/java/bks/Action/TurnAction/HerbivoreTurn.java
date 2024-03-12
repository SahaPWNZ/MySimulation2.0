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
    public void makeTurnAction(Simulation simulation) {//весь ход сущности
        ArrayList<Entity> listHerbivore = Creature.getListOfEntity(Herbivore.class, simulation.getGameMap());
        for (Entity entity : listHerbivore) {
            Herbivore herbivore = (Herbivore) entity;
            if (herbivore.isEatInNeighbors(simulation.getGameMap(), Grass.class)) {
                herbivore.eat(simulation.getGameMap());
            } else {
                makeMoveToEat(herbivore, simulation);
            }
        }
    }

    private void makeMoveToEat(Herbivore herbivore, Simulation simulation) {//сущность делает шаг в сторону пищи
        ArrayList<Entity> listOfEat = Creature.getListOfEntity(Grass.class, simulation.getGameMap());
        if (!listOfEat.isEmpty()) {
            BFS bfs = new BFS(herbivore.getCoordinates(), herbivore.getCoordinatesOfClosestEat(simulation.getGameMap(), listOfEat));
            Coordinates turnCoordinates = bfs.runBFS(simulation);
            if (turnCoordinates != null) {
                herbivore.makeMove(simulation.getGameMap(), herbivore.getCoordinates(), turnCoordinates);
            } else {
                herbivore.makeRandomMove(simulation.getGameMap());
            }
        } else {
            herbivore.makeRandomMove(simulation.getGameMap());
        }
    }
}
