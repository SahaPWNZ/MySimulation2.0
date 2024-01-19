package bks.Entities;

public abstract class Creature {
    //координаты
    final int SPEED;
    final int HP;

    Creature(int speed, int hp) {
        this.SPEED = speed;
        this.HP = hp;
    }
    abstract void makeMove();
}
