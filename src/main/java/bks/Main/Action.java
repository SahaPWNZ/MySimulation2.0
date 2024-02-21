package bks.Main;

import bks.Entities.*;

import java.util.ArrayList;
import java.util.Random;

public class Action {

    private static final Random random = new Random(); //рандом необходимый для генерации существ в свободных случайных ячейках

    //инит экшн при создании симуляции
    private static void initStaticEntity(Simulation simulation) { //метод создающий статические объекты
        int count = 3;
        while (count > 0) {
            Coordinates coordinatesRock = new Coordinates(random.nextInt(simulation.getGameMap().getHEIGHT()), random.nextInt(simulation.getGameMap().getWIDTH()));
            if (simulation.getGameMap().isEmptyCeil(coordinatesRock)) {
                simulation.getGameMap().getMap().put(coordinatesRock, new Rock(coordinatesRock));
                count--;
            }
            Coordinates coordinatesTree = new Coordinates(random.nextInt(simulation.getGameMap().getHEIGHT()), random.nextInt(simulation.getGameMap().getWIDTH()));
            if (simulation.getGameMap().isEmptyCeil(coordinatesTree)) {
                simulation.getGameMap().getMap().put(coordinatesTree, new Tree(coordinatesTree));
                count--;
            }
        }
    }

    private static void initGrassEntity(Simulation simulation) { //метод создающий траву
        int count = 3;
        while (count > 0) {
            Coordinates coordinatesGrass = new Coordinates(random.nextInt(simulation.getGameMap().getHEIGHT()), random.nextInt(simulation.getGameMap().getWIDTH()));
            if (simulation.getGameMap().isEmptyCeil(coordinatesGrass)) {
                simulation.getGameMap().getMap().put(coordinatesGrass, new Grass(coordinatesGrass));
                count--;
            }
        }
    }

    private static void initHerbivoreEntity(Simulation simulation) { //метод создающий травоядных
        int count = 2;
        while (count > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(simulation.getGameMap().getHEIGHT()), random.nextInt(simulation.getGameMap().getWIDTH()));
            if (simulation.getGameMap().isEmptyCeil(coordinates)) {
                simulation.getGameMap().getMap().put(coordinates, new Herbivore(coordinates));
                count--;
            }
        }
    }

    private static void initPredatorEntity(Simulation simulation) { //метод создающий хищников
        int count = 1;
        while (count > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(simulation.getGameMap().getHEIGHT()), random.nextInt(simulation.getGameMap().getWIDTH()));
            if (simulation.getGameMap().isEmptyCeil(coordinates)) {
                simulation.getGameMap().getMap().put(coordinates, new Predator(coordinates));
                count--;
            }
        }
    }

    public static void initializeEntities(Simulation simulation) {
        initGrassEntity(simulation);
        initHerbivoreEntity(simulation);
        initStaticEntity(simulation);
        initPredatorEntity(simulation);
    }

    //ход всей симуляции (действия травоядных, хищников, проверка на добавление

    private static void initEatEntity(Simulation simulation) {
        if (Herbivore.entities.size() <= 1) {
            initHerbivoreEntity(simulation);
        }
        if (Creature.getListOfEats(Grass.class, simulation).size() <= 1) {
            initGrassEntity(simulation);
        }
    }

    private static void nextTurn(Simulation simulation) { //метод делающий ход симуляции: ход хищников, ход травоядных, затем проверка, стоит ли добавить ещё пищи на поле
        nextTurnHerbivore(Herbivore.entities, simulation);
        nextTurnPredator(Predator.entities, simulation);
        Action.initEatEntity(simulation);
    }


    private static Coordinates getCoordinatesOfClosestEat(Simulation simulation, Coordinates selfCoordinate, ArrayList<Entity> listOfEat) { //метод который ищет местонахождение ближайшей еды, путем модуля разности координат
        int selfVectorCoordinate = selfCoordinate.getCol() + selfCoordinate.getRow();
        int minEatVector = simulation.getGameMap().getHEIGHT() + simulation.getGameMap().getWIDTH() + 2;
        Coordinates result = null;
        for (Entity entity : listOfEat) {
            int eatVectorCoordinate = entity.getCoordinates().getCol() + entity.getCoordinates().getRow();
            if (minEatVector > Math.abs(eatVectorCoordinate - selfVectorCoordinate)) {
                minEatVector = Math.abs(eatVectorCoordinate - selfVectorCoordinate);
                result = entity.getCoordinates();
            }
        }
        return result;
    }

    private static void nextTurnHerbivore(ArrayList<Herbivore> herbivores, Simulation simulation) { //метод отвечающий полностью за ход травоядного
        for (Herbivore herbivore : herbivores) {
            if (herbivore.findEat(simulation)) {
                herbivore.eat(simulation);
            } else {
                ArrayList<Entity> listOfEat = Creature.getListOfEats(Grass.class, simulation);
                if (!listOfEat.isEmpty()) {
                    BFS bfs = new BFS(herbivore.getCoordinates(), getCoordinatesOfClosestEat(simulation, herbivore.getCoordinates(), listOfEat));
                    Coordinates turnCoordinates = bfs.run(simulation);
                    if (turnCoordinates != null) {
                        herbivore.makeMove(simulation, herbivore.getCoordinates(), turnCoordinates);
                    } else {
                        herbivore.randomMove(simulation);
                    }
                } else {
                    herbivore.randomMove(simulation);
                }
            }
        }
    }

    private static void nextTurnPredator(ArrayList<Predator> predators, Simulation simulation) { //метод отвечающий полностью за ход хищника
        for (Predator predator : predators) {
            int countSpeed = 0;
            while (countSpeed < predator.getSPEED()) {
                if (predator.findEat(simulation)) {
                    predator.eat(simulation);
                } else {
                    ArrayList<Entity> listOfEat = Creature.getListOfEats(Herbivore.class, simulation);
                    if (!listOfEat.isEmpty()) {
                        BFS bfs = new BFS(predator.getCoordinates(), getCoordinatesOfClosestEat(simulation, predator.getCoordinates(), listOfEat));
                        Coordinates turnCoordinates = bfs.run(simulation);
                        if (turnCoordinates != null) {
                            predator.makeMove(simulation, predator.getCoordinates(), turnCoordinates);
                        } else {
                            predator.randomMove(simulation);
                        }
                    } else {
                        predator.randomMove(simulation);
                    }
                }
                countSpeed++;
            }

        }
    }


}
