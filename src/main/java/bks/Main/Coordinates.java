package bks.Main;

import java.util.ArrayList;
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

    public ArrayList<Coordinates> getNeighbors(GameMap map) {
        ArrayList<Coordinates> listOfNeighbors = new ArrayList<>();
        for (int i = this.row - 1; i <= this.row + 1; i++) {
            for (int j = this.col - 1; j <= this.col + 1; j++) {
                if (!(i == this.row && j == this.col) && (i <map.getHeight() && i> 0) && (j < map.getWidth() && j > 0)) {
                    if (map.isEmptyCeil(new Coordinates(i, j))) {
                        listOfNeighbors.add(new Coordinates(i, j));
                    }
                }
            }
        }

        return listOfNeighbors;
    }
}
