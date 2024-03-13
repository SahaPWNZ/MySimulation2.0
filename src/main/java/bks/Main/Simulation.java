package bks.Main;

import bks.Action.Action;
import bks.Action.InitAction.*;
import bks.Action.TurnAction.HerbivoreTurn;
import bks.Action.TurnAction.PredatorTurn;
import bks.Action.TurnAction.TurnAction;

import java.util.*;


public class Simulation {
    protected static Scanner scan = new Scanner(System.in); //сканер для продолжения/оконачания симуляции
    private int countTurn; //счётчик ходов
    private final GameMap gameMap;
    private static final int WIDTH = 9;
    private static final int HEIGHT = 6;

    private Simulation(int width, int height) {
        countTurn = 0;
        gameMap = new GameMap(width, height);
    }

    private int getCountTurn() {
        return countTurn;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    private void addCountTurn() {
        countTurn = getCountTurn() + 1;
    }


    private void renderMap() { //метод отвечающий за рендер поля
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

    private void makeNextTurn() { //метод делающий ход симуляции: ход хищников, ход травоядных, и спавн доп сущностей
        ArrayList<Action> turnAction = new ArrayList<>();
        turnAction.add(new HerbivoreTurn());
        turnAction.add(new PredatorTurn());
        turnAction.add(new SpawnAction());
        for (Action action : turnAction) {
            if (action instanceof TurnAction) {
                ((TurnAction) action).makeTurnAction(getGameMap());
            } else {
                ((SpawnAction) action).makeAction(getGameMap());
            }
        }
        addCountTurn();
        renderMap();
    }


    public static void startSimulation() { //метод начинающий симуляцию
        System.out.println("Нажмите любую клавишу чтобы начать симуляцию");
        scan.nextLine();
        Simulation simulation = new Simulation(WIDTH, HEIGHT);
        InitSpawnAction initAction = new InitSpawnAction();
        initAction.makeAction(simulation.getGameMap());
        simulation.renderMap();
        while (true) {
            System.out.println("Нажмите любую клавишу чтобы продолжить симуляцию, или 0 чтобы закончить. Текущий ход = " + simulation.getCountTurn());
            String input = scan.nextLine();
            if (input.equals("0")) {
                System.exit(0);
            }
            simulation.makeNextTurn();
        }
    }


}
