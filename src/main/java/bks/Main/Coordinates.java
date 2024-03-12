package bks.Main;

public record Coordinates(int row, int col) {

    @Override
    public String toString() {
        return "Coordinates{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }

    public static boolean isValidCoordinates(Coordinates coord, GameMap map) { //проверяет координату на валидность
        return (coord.row() < map.getHEIGHT() && coord.row() >= 0) && (coord.col() < map.getWIDTH() && coord.col() >= 0);
    }

    public int[][] getArrayOfCoordinatesNeighbors() { // возвращает массив массивов значений координат соседей
        return new int[][]{{-1 + this.row(), this.col()},
                {1 + this.row(), this.col()},
                {this.row(), this.col() - 1},
                {this.row(), this.col() + 1}};
    }
}
