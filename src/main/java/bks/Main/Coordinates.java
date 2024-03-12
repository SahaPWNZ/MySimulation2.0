package bks.Main;

import java.util.Objects;

public class Coordinates {
    private final int col, row;

    public Coordinates(int row, int col) {
        this.col = col;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return col == that.col && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public static boolean validCoordinates(Coordinates coord, GameMap map) {
        return (coord.getRow() < map.getHEIGHT() && coord.getRow() >= 0) && (coord.getCol() < map.getWIDTH() && coord.getCol() >= 0);
    }

    public int[][] getArrayOfCoordinatesNeighbors() {
        return new int[][]{{-1 + this.getRow(), this.getCol()},
                {1 + this.getRow(), this.getCol()},
                {this.getRow(), this.getCol() - 1},
                {this.getRow(), this.getCol() + 1}};
    }
}
