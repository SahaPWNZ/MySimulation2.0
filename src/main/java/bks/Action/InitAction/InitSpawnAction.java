package bks.Action.InitAction;

import bks.Entities.Entity;
import bks.Main.GameMap;

import java.util.Map;

public final class InitSpawnAction extends InitAction {

    public InitSpawnAction(GameMap map) {
        super(map);
    }
    @Override
    public void makeAction() {
        for (Map.Entry<Class<? extends Entity>, Integer> entry : entityMap.entrySet()) {
            initEntity(entry.getKey(), 0);
        }
    }

}
