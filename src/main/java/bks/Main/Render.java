package bks.Main;

public class Render {




    public static void mapRender(GameMap map){


        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.isEmptyCeil(new Coordinates(i, j))) {
                    System.out.print("O" + " ");
                }
                else{
                    System.out.print(map.getMap().get(new Coordinates(i, j)) + " ");
                }
            }
            System.out.println();
        }
    }
}
