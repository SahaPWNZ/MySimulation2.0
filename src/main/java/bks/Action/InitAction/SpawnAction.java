package bks.Action.InitAction;

import bks.Entities.*;
import bks.Main.GameMap;


public final class SpawnAction extends InitAction {

    public SpawnAction(GameMap map) {
        super(map);
    }

    @Override
    public void makeAction() {
        if (Creature.getListOfEntity(Herbivore.class, map).size() <= 1) {
            initEntity(Herbivore.class, Creature.getListOfEntity(Herbivore.class, map).size());
        }
        if (Creature.getListOfEntity(Grass.class, map).size() <= 1) {
            initEntity(Grass.class, Creature.getListOfEntity(Grass.class, map).size());
        }

    }
}
