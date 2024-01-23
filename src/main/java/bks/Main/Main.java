package bks.Main;

import bks.Entities.Creature;
import bks.Entities.Predator;

public class Main {
    public static void main(String[] args) {
        Creature wolf = new Predator(5, 10, 3, new Coordinates(0,0));
        System.out.println("Hello world!");
        System.out.println(wolf.getCoordinates());
        wolf.setCoordinates(new Coordinates(1,1));
        System.out.println(wolf.getCoordinates());
    }
}