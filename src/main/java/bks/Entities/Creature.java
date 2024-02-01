package bks.Entities;

import bks.Main.Coordinates;

public abstract class Creature extends Entity {
    private final int SPEED;
    private int HP;



    Creature(int speed, int hp, Coordinates coordinates) {
        this.SPEED = speed;
        this.HP = hp;
        this.coordinates = coordinates;
    }

    public int getSPEED() {
        return SPEED;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    abstract void makeMove();
}
