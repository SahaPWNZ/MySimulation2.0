package bks.Main;

import bks.Entities.*;

import java.util.*;


public class Simulation {
    public static Random random = new Random(); //рандом необходимый для генерации существ в свободных случайных ячейках
    public static Scanner scan = new Scanner(System.in); //сканер для продолжения/оконачания симуляции
    private int countTurn = 0; //счётчик ходов
    private final GameMap gameMap = new GameMap(6, 4);

    private int getCountTurn() {
        return countTurn;
    }

    private void setCountTurn() {
        this.countTurn = this.getCountTurn() + 1;
    }

    private void mapRender() { //метод отвечающий за рендер поля
        for (int i = 0; i < gameMap.getHEIGHT(); i++) {
            for (int j = 0; j < gameMap.getWIDTH(); j++) {
                if (gameMap.isEmptyCeil(new Coordinates(i, j))) {
                    System.out.print("_" + " ");
                } else {
                    System.out.print(gameMap.getMap().get(new Coordinates(i, j)) + " ");
                }
            }
            System.out.println();
        }

    }

    public GameMap getGameMap() {
        return gameMap;
    }

    private static void nextTurn(Simulation simulation) { //метод делающий ход симуляции: ход хищников, ход травоядных, затем проверка, стоит ли добавить ещё пищи на поле
        simulation.nextTurnHerbivore(Herbivore.entities);
        simulation.nextTurnPredator(Predator.entities);
        if (Herbivore.entities.size() <= 1) {
            simulation.initHerbivoreEntity();
        }
        if (Creature.getListOfEats(Grass.class, simulation).size() <= 1) {
            simulation.initGrassEntity();
        }
        simulation.setCountTurn();
        simulation.mapRender();
    }

    private Coordinates getCoordinatesOfClosestEat(Coordinates selfCoordinate, ArrayList<Entity> listOfEat) { //метод который ищет местонахождение ближайшей еды, путем модуля разности координат
        int selfVectorCoordinate = selfCoordinate.getCol() + selfCoordinate.getRow();
        int minEatVector = gameMap.getHEIGHT() + gameMap.getWIDTH() + 2;
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

    private void nextTurnHerbivore(ArrayList<Herbivore> herbivores) { //метод отвечающий полностью за ход травоядного
        for (Herbivore herbivore : herbivores) {
            if (herbivore.findEat(this)) {
                herbivore.eat(this);
            } else {
                ArrayList<Entity> listOfEat = Creature.getListOfEats(Grass.class, this);
                if (!listOfEat.isEmpty()) {
                    BFS bfs = new BFS(herbivore.getCoordinates(), getCoordinatesOfClosestEat(herbivore.getCoordinates(), listOfEat));
                    Coordinates turnCoordinates = bfs.run(this);
                    if (turnCoordinates != null) {
                        herbivore.makeMove(this, herbivore.getCoordinates(), turnCoordinates);
                    } else {
                        herbivore.randomMove(this);
                    }
                } else {
                    herbivore.randomMove(this);
                }
            }
        }
    }

    private void nextTurnPredator(ArrayList<Predator> predators) { //метод отвечающий полностью за ход хищника
        for (Predator predator : predators) {
            int countSpeed = 0;
            while (countSpeed < predator.getSPEED()) {
                if (predator.findEat(this)) {
                    predator.eat(this);
                } else {
                    ArrayList<Entity> listOfEat = Creature.getListOfEats(Herbivore.class, this);
                    if (!listOfEat.isEmpty()) {
                        BFS bfs = new BFS(predator.getCoordinates(), getCoordinatesOfClosestEat(predator.getCoordinates(), listOfEat));
                        Coordinates turnCoordinates = bfs.run(this);
                        if (turnCoordinates != null) {
                            predator.makeMove(this, predator.getCoordinates(), turnCoordinates);
                        } else {
                            predator.randomMove(this);
                        }
                    } else {
                        predator.randomMove(this);
                    }
                }
                countSpeed++;
            }

        }
    }

    public static void startSimulation() { //метод начинающий симуляцию
        System.out.println("Нажмите любую клавишу чтобы начать симуляцию");
        scan.nextLine();
        Simulation simulation = new Simulation();
        simulation.initGrassEntity();
        simulation.initHerbivoreEntity();
        simulation.initPredatorEntity();
        simulation.initStaticEntity();
        simulation.mapRender();

        while (true) {
            System.out.println("Нажмите любую клавишу чтобы продолжить симуляцию, или 0 чтобы закончить. Текущий ход = " + simulation.getCountTurn());
            String input = scan.nextLine();
            if (input.equals("0")) {
                System.exit(0);
            }
            nextTurn(simulation);
        }
    }

    private void initStaticEntity() { //метод создающий статические объекты
        int count = 3;
        while (count > 0) {
            Coordinates coordinatesRock = new Coordinates(random.nextInt(gameMap.getHEIGHT()), random.nextInt(gameMap.getWIDTH()));
            if (gameMap.isEmptyCeil(coordinatesRock)) {
                gameMap.getMap().put(coordinatesRock, new Rock(coordinatesRock));
                count--;
            }
            Coordinates coordinatesTree = new Coordinates(random.nextInt(gameMap.getHEIGHT()), random.nextInt(gameMap.getWIDTH()));
            if (gameMap.isEmptyCeil(coordinatesTree)) {
                gameMap.getMap().put(coordinatesTree, new Tree(coordinatesTree));
                count--;
            }
        }
    }

    private void initGrassEntity() { //метод создающий траву
        int count = 3;
        while (count > 0) {
            Coordinates coordinatesGrass = new Coordinates(random.nextInt(gameMap.getHEIGHT()), random.nextInt(gameMap.getWIDTH()));
            if (gameMap.isEmptyCeil(coordinatesGrass)) {
                gameMap.getMap().put(coordinatesGrass, new Grass(coordinatesGrass));
                count--;
            }
        }
    }

    private void initHerbivoreEntity() { //метод создающий травоядных
        int count = 2;
        while (count > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(gameMap.getHEIGHT()), random.nextInt(gameMap.getWIDTH()));
            if (gameMap.isEmptyCeil(coordinates)) {
                gameMap.getMap().put(coordinates, new Herbivore(coordinates));
                count--;
            }
        }
    }

    private void initPredatorEntity() { //метод создающий хищников
        int count = 1;
        while (count > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(gameMap.getHEIGHT()), random.nextInt(gameMap.getWIDTH()));
            if (gameMap.isEmptyCeil(coordinates)) {
                gameMap.getMap().put(coordinates, new Predator(coordinates));
                count--;
            }
        }
    }

}
