package bks.Main;

import bks.Entities.*;

import java.util.*;

public class Simulation {
    public static Random random = new Random();
    public static Scanner scan = new Scanner(System.in);
    private int countTurn = 0;
    private HashMap<Coordinates, Entity> map = new HashMap<>();
    private final int WIDTH;
    private final int HEIGHT;


    public Simulation(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    public HashMap<Coordinates, Entity> getMap() {
        return map;
    }

    public boolean isEmptyCeil(Coordinates coordinates) {
        return this.map.get(coordinates) == null;
    }

    public void makeEmptyCeil(Coordinates coordinates) {
        this.map.put(coordinates, null);
    }

    public boolean isEmptyCeil(Coordinates coordinates, Coordinates target) {
        return this.map.get(coordinates) == null || coordinates.equals(target);
    }


    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getCountTurn() {
        return countTurn;
    }

    public void setCountTurn() {
        this.countTurn = this.getCountTurn() +1;
    }

    public void mapRender() {
        for (int i = 0; i < this.getHEIGHT(); i++) {
            for (int j = 0; j < this.getWIDTH(); j++) {
                if (this.isEmptyCeil(new Coordinates(i, j))) {
                    System.out.print("_" + " ");
                } else {
                    System.out.print(this.getMap().get(new Coordinates(i, j)) + " ");
                }
            }
            System.out.println();
        }

    }

    public void nextTurn() {
    }

    public ArrayList<Entity> getListOfEats(Class<?> eat) {
        ArrayList<Entity> result = new ArrayList<>();
        for (Map.Entry<Coordinates, Entity> entry : this.getMap().entrySet()) {
            if (eat.isInstance(entry.getValue())) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public Coordinates getCoordinatesOfClosestEat(Coordinates selfCoordinate, ArrayList<Entity> listOfEat) {
        int selfVectorCoordinate = selfCoordinate.getCol() + selfCoordinate.getRow();
        int minEatVector = getHEIGHT() + getWIDTH() + 2;
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

    public void nextTurnHerbivore(ArrayList<Herbivore> herbivores) {
        for (Herbivore herbivore : herbivores) {
            if (herbivore.findEat(this)) {
                herbivore.eat(this);
            } else {
                ArrayList<Entity> listOfEat = getListOfEats(Grass.class);
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
    public void nextTurnPredator() {

    }

    public static void startSimulation() {
        System.out.println("Нажмите любую клавишу чтобы начать симуляцию");
        scan.nextLine();
        Simulation simulation = new Simulation(12, 6);
        simulation.initGrassEntity();
        simulation.initHerbivoreEntity();
        simulation.initPredatorEntity();
        simulation.initStaticEntity();
        simulation.mapRender();
        while (true) {
            System.out.println("Нажмите любую клавишу чтобы продолжить симуляцию, или 0 чтобы закончить. Текущий ход = " + simulation.getCountTurn());
            String input = scan.nextLine();
            if (input.equals("0")){
                System.exit(0);
            }
            simulation.nextTurnHerbivore(Herbivore.entities);
            simulation.nextTurnPredator();
            //проверка на количество травы и травоядных, если меньше 1-ого, то добавляем
            if (Herbivore.entities.size() <= 1){
                simulation.initHerbivoreEntity();
            }
            if (simulation.getListOfEats(Grass.class).size() <=1){
                simulation.initGrassEntity();
            }
            simulation.setCountTurn();
            simulation.mapRender();

        }
//        String input = scan.nextLine();
//        if (input.equals("0")) {
//            System.exit(0);
//        }
    }

    public void initStaticEntity() {
        int count = 6;
        while (count > 0) {
            Coordinates coordinatesRock = new Coordinates(random.nextInt(this.getHEIGHT()), random.nextInt(this.getWIDTH()));
            if (this.isEmptyCeil(coordinatesRock)) {
                this.getMap().put(coordinatesRock, new Rock(coordinatesRock));
                count--;
            }
            Coordinates coordinatesTree = new Coordinates(random.nextInt(this.getHEIGHT()), random.nextInt(this.getWIDTH()));
            if (this.isEmptyCeil(coordinatesTree)) {
                this.getMap().put(coordinatesTree, new Tree(coordinatesTree));
                count--;
            }
        }
    }

    public void initGrassEntity() {
        int count = 4;
        while (count > 0) {
            Coordinates coordinatesGrass = new Coordinates(random.nextInt(this.getHEIGHT()), random.nextInt(this.getWIDTH()));
            if (this.isEmptyCeil(coordinatesGrass)) {
                this.getMap().put(coordinatesGrass, new Grass(coordinatesGrass));
                count--;
            }
        }
    }

    public void initHerbivoreEntity() {
        int count = 3;
        while (count > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(this.getHEIGHT()), random.nextInt(this.getWIDTH()));
            if (this.isEmptyCeil(coordinates)) {
                this.getMap().put(coordinates, new Herbivore(coordinates));
                count--;
            }
        }
    }

    public void initPredatorEntity() {

    }

}
