package bks.Action.InitAction;

import bks.Entities.*;
import bks.Main.Coordinates;
import bks.Main.GameMap;
import bks.Entities.Rock;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class InitActionTest extends InitAction {
    public HashMap <String, Integer> mapConstEntityValue = new HashMap<>();
    {
        mapConstEntityValue.put("Herbivore", 2);
        mapConstEntityValue.put("Predator", 1);
        mapConstEntityValue.put("Grass", 4);
        mapConstEntityValue.put("Rock", 3);
        mapConstEntityValue.put("Tree", 3);
    }
    public HashMap<String, Integer> mapInitEntity = new HashMap<>();
    {
        mapInitEntity.put("Herbivore", 0);
        mapInitEntity.put("Predator", 0);
        mapInitEntity.put("Grass", 0);
        mapInitEntity.put("Rock", 0);
        mapInitEntity.put("Tree", 0);
    }

    public void addInMapEntity(String entityName) {
        if (mapInitEntity.containsKey(entityName)) {
            mapInitEntity.put(entityName, mapInitEntity.get(entityName) + 1);
        } else {
            mapInitEntity.putIfAbsent(entityName, 1);
        }
    }

    public void printMapEntity() {
        for (Map.Entry<String, Integer> entry : mapInitEntity.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        }
    }

    @Override
    public void makeAction(GameMap map)  {

    }

//    @Override
//    public void makeAction(GameMap map) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
//        this.makeEntityHashMap(map);
//        for (Map.Entry<String, Integer> entry : mapConstEntityValue.entrySet()){
//            if (mapInitEntity.get(entry.getKey()) <= entry.getValue()){
//                int value = mapInitEntity.get(entry.getKey());
//                while (value <= entry.getValue()) {
//                    Coordinates coordinates = new Coordinates(random.nextInt(map.getHEIGHT()), random.nextInt(map.getWIDTH()));
//                    if (map.isEmptyCeil(coordinates)) {
//                        Class<?> clazz = Class.forName(entry.getKey());
//                        Constructor<?> constructor = clazz.getConstructor(Coordinates.class);
//                        Entity entity = (Entity) constructor.newInstance(coordinates);
//                        map.getMap().put(coordinates, entity);
//                        value++;
//                    }
//                }
//            }
//        }
//    }
//
//
//    private void makeEntityHashMap(GameMap map) {
//        for (int i = 0; i < map.getHEIGHT(); i++) {
//            for (int j = 0; j < map.getWIDTH(); j++) {
//                if (map.isEmptyCeil(new Coordinates(i, j))) {
//                    System.out.print("_" + " ");
//                } else {
//                    this.addInMapEntity(map.getMap().get(new Coordinates(i, j)).getClass().getName().substring(13));
//                }
//            }
//        }
//    }
}
