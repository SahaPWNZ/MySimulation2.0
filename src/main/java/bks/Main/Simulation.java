package bks.Main;

import bks.Action.Action;
import bks.Action.InitAction.*;
import bks.Action.TurnAction.HerbivoreTurn;
import bks.Action.TurnAction.PredatorTurn;
import bks.Action.TurnAction.TurnAction;
import bks.Entities.*;

import java.util.*;


public class Simulation {
    public static Random random = new Random(); //рандом необходимый для генерации существ в свободных случайных ячейках
    public static Scanner scan = new Scanner(System.in); //сканер для продолжения/оконачания симуляции
    private int countTurn = 0; //счётчик ходов
    private final GameMap gameMap = new GameMap(8, 5);
    private final ArrayList<InitAction> initAction;
    private ArrayList<Action> turnAction;


    public Simulation() {
        this.initAction = new ArrayList<InitAction>();
    }

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
        simulation.turnAction = new ArrayList<>();
        simulation.turnAction.add(new HerbivoreTurn());
        simulation.turnAction.add(new PredatorTurn());
        if (Herbivore.entities.size() <= 1) {
            simulation.turnAction.add(new initHerbivores());
        }
        if (Creature.getListOfEats(Grass.class, simulation).size() <= 1) {
            simulation.turnAction.add(new InitGrassSpawn());
        }
        for (Action action : simulation.turnAction) {
            if (action instanceof TurnAction) {
                ((TurnAction) action).makeTurnAction(simulation);
            } else {
                ((InitAction) action).makeAction(simulation);
            }
        }
        simulation.setCountTurn();
        simulation.mapRender();
    }


    public static void startSimulation() { //метод начинающий симуляцию
        System.out.println("Нажмите любую клавишу чтобы начать симуляцию");
        scan.nextLine();
        Simulation simulation = new Simulation();
        simulation.createInitActions();
        for (InitAction action : simulation.initAction) {
            action.makeAction(simulation);
        }
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

    public void createInitActions() {
        initAction.add(new initHerbivores());
        initAction.add(new InitStaticObject());
        initAction.add(new InitGrassSpawn());
        initAction.add(new InitPredatorsSpawn());
    }
}
