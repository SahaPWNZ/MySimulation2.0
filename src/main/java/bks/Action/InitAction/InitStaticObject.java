package bks.Action.InitAction;

import bks.Entities.Rock;
import bks.Entities.Tree;
import bks.Main.Coordinates;
import bks.Main.GameMap;
import bks.Main.Simulation;

public class InitStaticObject extends InitAction {
    private int staticObject = 4;
    @Override
    public void makeAction(GameMap map) {
        while (staticObject > 0) {
            Coordinates coordinatesRock = new Coordinates(random.nextInt(map.getHEIGHT()), random.nextInt(map.getWIDTH()));
            if (map.isEmptyCeil(coordinatesRock)) {
                map.getMap().put(coordinatesRock, new Rock(coordinatesRock));
                staticObject--;
            }
            Coordinates coordinatesTree = new Coordinates(random.nextInt(map.getHEIGHT()), random.nextInt(map.getWIDTH()));
            if (map.isEmptyCeil(coordinatesTree)) {
                map.getMap().put(coordinatesTree, new Tree(coordinatesTree));
                staticObject--;
            }
        }
    }
}
