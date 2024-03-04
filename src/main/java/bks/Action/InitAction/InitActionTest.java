package bks.Action.InitAction;

import bks.Main.GameMap;

import java.util.HashMap;
import java.util.Map;

public class InitActionTest extends InitAction {
    public  HashMap<String, Integer> mapInitEntity =  new HashMap<>();


    public void addInMapEntity(String entityName) {
        if (mapInitEntity.containsKey(entityName)) {
            mapInitEntity.put(entityName, mapInitEntity.get(entityName) + 1);
        } else {
            mapInitEntity.putIfAbsent(entityName, 1);
        }
    }
public void printMapEntity(){
        for (Map.Entry<String, Integer> entry : mapInitEntity.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        }
}
    @Override
    public void makeAction(GameMap map) {
        if (mapInitEntity.get("Herbivore") <=1){

        }
    }
}
