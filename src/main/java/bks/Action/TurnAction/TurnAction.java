package bks.Action.TurnAction;

import bks.Action.Action;
import bks.Main.GameMap;

public abstract class TurnAction extends Action {
    public abstract void makeTurnAction(GameMap map);
}
