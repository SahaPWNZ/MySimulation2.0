package bks.Action.InitAction;

import bks.Main.GameMap;

public final class InitSpawnAction extends InitAction {

    @Override
    public void makeAction(GameMap map) {
        initHerbivore(map, 0);
        initPredator(map, 0);
        initRock(map, 0);
        initGrass(map, 0);
        initTree(map, 0);
    }

}
