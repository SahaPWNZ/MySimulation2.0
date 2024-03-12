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
        if (Herbivore.entities.size() <= 1){
            initHerbivore(map, Herbivore.entities.size());
        }
    }

    private void checkAndSpawnGrass(GameMap map) {
        if (Creature.getListOfEats(Grass.class, map).size() <= 1) {
            initGrass(map, Creature.getListOfEats(Grass.class, map).size());
        }
    }
}
