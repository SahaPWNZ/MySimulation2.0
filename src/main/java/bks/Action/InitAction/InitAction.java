package bks.Action.InitAction;

import bks.Action.Action;
import bks.Main.GameMap;
import bks.Main.Simulation;

import java.util.Random;

public abstract class InitAction extends Action {
    public static Random random = new Random();
    public abstract void makeAction(GameMap map);
}
