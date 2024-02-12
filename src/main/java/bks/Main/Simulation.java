package bks.Main;

import bks.Entities.*;

import java.util.*;

public class Simulation {
    public static Random random = new Random(); //рандом необходимый для генерации существ в свободных случайных ячейках
    public static Scanner scan = new Scanner(System.in); //сканер для продолжения/оконачания симуляции
    private int countTurn = 0; //счётчик ходов
    private HashMap<Coordinates, Entity> map = new HashMap<>(); //структура хранящая координаты занятых сущностями ячеек
    private final int WIDTH;
    private final int HEIGHT;


    public Simulation(int width, int height) {
        WIDTH = width; //количество колонок поля
        HEIGHT = height; //количество строк поля
    }

    public HashMap<Coordinates, Entity> getMap() {
        return map;
    }

    public boolean isEmptyCeil(Coordinates coordinates) {
        return this.map.get(coordinates) == null; //проверка свободна ли ячейка
    }

    public void makeEmptyCeil(Coordinates coordinates) {
        this.map.put(coordinates, null); //освобождает ячейку по заданным координатам
    }

    public boolean isEmptyCeil(Coordinates coordinates, Coordinates target) { //ещё одна вариация проверки свободной ячейки, применяемая в алгоритме поиска пути
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
        this.countTurn = this.getCountTurn() + 1;
    }

    public void mapRender() { //метод отвечающий за рендер поля
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

    public static void nextTurn(Simulation simulation) { //метод делающий ход симуляции: ход хищников, ход травоядных, затем проверка, стоит ли добавить ещё пищи на поле
        simulation.nextTurnHerbivore(Herbivore.entities);
        simulation.nextTurnPredator(Predator.entities);
        if (Herbivore.entities.size() <= 1) {
            simulation.initHerbivoreEntity();
        }
        if (simulation.getListOfEats(Grass.class).size() <= 1) {
            simulation.initGrassEntity();
        }
        simulation.setCountTurn();
        simulation.mapRender();
    }

    public ArrayList<Entity> getListOfEats(Class<?> eat) { //метод возвращающий список заданного класса сущностей
        ArrayList<Entity> result = new ArrayList<>();
        for (Map.Entry<Coordinates, Entity> entry : this.getMap().entrySet()) {
            if (eat.isInstance(entry.getValue())) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public Coordinates getCoordinatesOfClosestEat(Coordinates selfCoordinate, ArrayList<Entity> listOfEat) { //метод который ищет местонахождение ближайшей еды, путем модуля разности координат
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

    public void nextTurnHerbivore(ArrayList<Herbivore> herbivores) { //метод отвечающий полностью за ход травоядного
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

    public void nextTurnPredator(ArrayList<Predator> predators) { //метод отвечающий полностью за ход хищника
        for (Predator predator : predators) {
            int countSpeed = 0;
            while (countSpeed < predator.getSPEED()) {
                if (predator.findEat(this)){
                    predator.eat(this);
                }
                else {
                    ArrayList<Entity> listOfEat = getListOfEats(Herbivore.class);
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
        Simulation simulation = new Simulation(12, 6);
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

    public void initStaticEntity() { //метод создающий статические объекты
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

    public void initGrassEntity() { //метод создающий траву
        int count = 4;
        while (count > 0) {
            Coordinates coordinatesGrass = new Coordinates(random.nextInt(this.getHEIGHT()), random.nextInt(this.getWIDTH()));
            if (this.isEmptyCeil(coordinatesGrass)) {
                this.getMap().put(coordinatesGrass, new Grass(coordinatesGrass));
                count--;
            }
        }
    }

    public void initHerbivoreEntity() { //метод создающий травоядных
        int count = 3;
        while (count > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(this.getHEIGHT()), random.nextInt(this.getWIDTH()));
            if (this.isEmptyCeil(coordinates)) {
                this.getMap().put(coordinates, new Herbivore(coordinates));
                count--;
            }
        }
    }

    public void initPredatorEntity() { //метод создающий хищников
        int count = 2;
        while (count > 0) {
            Coordinates coordinates = new Coordinates(random.nextInt(this.getHEIGHT()), random.nextInt(this.getWIDTH()));
            if (this.isEmptyCeil(coordinates)) {
                this.getMap().put(coordinates, new Predator(coordinates));
                count--;
            }
        }
    }

}
