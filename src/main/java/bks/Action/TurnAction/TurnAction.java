package bks.Action.TurnAction;

import bks.Action.Action;
import bks.Entities.Creature;
import bks.Entities.Entity;
import bks.Main.Simulation;

import java.util.ArrayList;

public abstract class TurnAction extends Action {
    public abstract void makeTurnAction(Simulation simulation);
}
