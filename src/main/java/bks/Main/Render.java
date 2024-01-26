package bks.Main;

public class Render {




    public static void mapRender(GameMap map){


        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.isEmpty(new Coordinates(i, j))) {
                    System.out.print("O" + " ");
                }
                else{
                    System.out.print(map.map.get(new Coordinates(i, j)) + " ");
                }
            }
            System.out.println();
        }
    }
}
