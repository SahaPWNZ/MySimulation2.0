package bks.Action.InitAction;

import bks.Entities.*;
import bks.Main.GameMap;


public final class SpawnAction extends InitAction {

    @Override
    public void makeAction(GameMap map) {
        checkAndSpawnGrass(map);
        checkAndSpawnHerbivore(map);
    }
    private void checkAndSpawnHerbivore(GameMap map) {
        if (Creature.getListOfEntity(Herbivore.class, map).size() <= 1){
            initHerbivore(map, Creature.getListOfEntity(Herbivore.class, map).size());
        }
    }

    private void checkAndSpawnGrass(GameMap map) {
        if (Creature.getListOfEntity(Grass.class, map).size() <= 1) {
            initGrass(map, Creature.getListOfEntity(Grass.class, map).size());
        }
    }
}
