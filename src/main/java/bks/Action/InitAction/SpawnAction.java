package bks.Action.InitAction;

import bks.Entities.*;
import bks.Main.GameMap;


public final class SpawnAction extends InitAction {

    public SpawnAction(GameMap map) {
        super(map);
    }

    @Override
    public void makeAction(GameMap map) {
        checkAndSpawnGrass(map);
        checkAndSpawnHerbivore(map);
    }

    private void checkAndSpawnHerbivore(GameMap map) {
        if (Creature.getListOfEntity(Herbivore.class, map).size() <= 1) {

        }
    }

    private void checkAndSpawnGrass(GameMap map) {
        if (Creature.getListOfEntity(Grass.class, map).size() <= 1) {

        }
    }
}
