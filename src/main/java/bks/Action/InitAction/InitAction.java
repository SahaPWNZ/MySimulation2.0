package bks.Action.InitAction;

import bks.Action.Action;
import bks.Entities.*;
import bks.Main.Coordinates;
import bks.Main.GameMap;

import java.util.HashMap;

public abstract class InitAction extends Action {
    GameMap map;

    public InitAction(GameMap map) {
        this.map = map;
    }

    protected final HashMap<Class<? extends Entity>, Integer> entityMap = new HashMap<>();

    {
        entityMap.put(Herbivore.class, 4);
        entityMap.put(Predator.class, 2);
        entityMap.put(Grass.class, 5);
        entityMap.put(Rock.class, 2);
        entityMap.put(Tree.class, 2);
    }

    public abstract void makeAction();

    protected void initEntity(Class<? extends Entity> entityClass, int countEntites) {
        while (countEntites < entityMap.get(entityClass)) {
            try {
                Coordinates coordinates = map.getRandomCoordinates();
                if (map.isEmptyCeil(coordinates)) {
                    map.getMap().put(coordinates, entityClass.getConstructor(Coordinates.class).newInstance(coordinates));
                    countEntites++;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
