package bks.Action.TurnAction;

import bks.Entities.Creature;
import bks.Entities.Entity;
import bks.Entities.Grass;
import bks.Entities.Herbivore;
import bks.Main.BFS;
import bks.Main.Coordinates;
import bks.Main.GameMap;

import java.util.ArrayList;

public final class HerbivoreTurn extends TurnAction {
    @Override
    public void makeTurnAction(GameMap map) {//весь ход сущности
        ArrayList<Entity> listHerbivore = Creature.getListOfEntity(Herbivore.class, map);
        for (Entity entity : listHerbivore) {
            Herbivore herbivore = (Herbivore) entity;
            if (herbivore.isEatInNeighbors(map, Grass.class)) {
                herbivore.eat(map, Grass.class);
            } else {
                makeMoveToEat(herbivore, map);
            }
        }
    }

    private void makeMoveToEat(Herbivore herbivore, GameMap map) {//сущность делает шаг в сторону пищи
        ArrayList<Entity> listOfEat = Creature.getListOfEntity(Grass.class, map);
        if (!listOfEat.isEmpty()) {
            BFS bfs = new BFS(herbivore.getCoordinates(), herbivore.getCoordinatesOfClosestEat(map, listOfEat));
            Coordinates turnCoordinates = bfs.runBFS(map);
            if (turnCoordinates != null) {
                herbivore.makeMove(map, herbivore.getCoordinates(), turnCoordinates);
            } else {
                herbivore.makeRandomMove(map);
            }
        } else {
            herbivore.makeRandomMove(map);
        }
    }
}
