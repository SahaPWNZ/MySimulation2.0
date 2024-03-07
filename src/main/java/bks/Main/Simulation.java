package bks.Main;

import bks.Action.Action;
import bks.Action.InitAction.*;
import bks.Action.TurnAction.HerbivoreTurn;
import bks.Action.TurnAction.PredatorTurn;
import bks.Action.TurnAction.TurnAction;
import bks.Entities.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class Simulation {
    public static Random random = new Random(); //рандом необходимый для генерации существ в свободных случайных ячейках
    public static Scanner scan = new Scanner(System.in); //сканер для продолжения/оконачания симуляции
    private HashMap<Entity, Integer> allEntity = new HashMap<>();
    private int countTurn; //счётчик ходов
    public final GameMap gameMap;
    private final ArrayList<InitAction> initAction;
    private ArrayList<Action> turnAction;


    public Simulation(int width, int height) {
        countTurn = 0;
        this.gameMap = new GameMap(width, height);
        this.initAction = new ArrayList<>();
    }

    private int getCountTurn() {
        return countTurn;
    }
    public GameMap getGameMap() {
        return gameMap;
    }
    private void addCountTurn(){
        countTurn = getCountTurn() +1;
    }
    public void createInitActions() {
        initAction.add(new initHerbivores());
        initAction.add(new InitStaticObject());
        initAction.add(new InitGrassSpawn());
        initAction.add(new InitPredatorsSpawn());
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

    private void nextTurn() { //метод делающий ход симуляции: ход хищников, ход травоядных, затем проверка, стоит ли добавить ещё пищи на поле
        turnAction = new ArrayList<>();
        turnAction.add(new HerbivoreTurn());
        turnAction.add(new PredatorTurn());

        if (Herbivore.entities.size() <= 1) {
           turnAction.add(new initHerbivores());
        }
        if (Creature.getListOfEats(Grass.class, getGameMap()).size() <= 1) {
            turnAction.add(new InitGrassSpawn());
        }
        for (Action action : turnAction) {
            if (action instanceof TurnAction) {
                ((TurnAction) action).makeTurnAction(this);
            } else {
                ((InitAction) action).makeAction(this.getGameMap());
            }
        }
        addCountTurn();
        mapRender();
    }


    public static void startSimulation() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException { //метод начинающий симуляцию
        System.out.println("Нажмите любую клавишу чтобы начать симуляцию");
        scan.nextLine();
        Simulation simulation = new Simulation(8, 5);
        simulation.createInitActions();
        for (InitAction action : simulation.initAction) {
            action.makeAction(simulation.getGameMap());
        }
        simulation.mapRender();

        while (true) {
            System.out.println("Нажмите любую клавишу чтобы продолжить симуляцию, или 0 чтобы закончить. Текущий ход = " + simulation.getCountTurn());
            String input = scan.nextLine();
            if (input.equals("0")) {
                System.exit(0);
            }
            simulation.nextTurn();
        }
    }


}
