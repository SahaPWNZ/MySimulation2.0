package bks.Action.InitAction;

import bks.Entities.*;
import bks.Main.GameMap;


public class SpawnAction extends InitAction {

    @Override
    public void makeAction(GameMap map) {
        checkAndSpawnGrass(map);
        checkAndSpawnHerbivore(map);
    }
    public void checkAndSpawnHerbivore(GameMap map) {
        if (Herbivore.entities.size() <= 1){
            initHerbivore(map, Herbivore.entities.size());
        }
    }

    public void checkAndSpawnGrass(GameMap map) {
        if (Creature.getListOfEats(Grass.class, map).size() <= 1) {
            initGrass(map, Creature.getListOfEats(Grass.class, map).size());
        }
    }
}
