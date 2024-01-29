package bks.Main;
import java.util.ArrayList;
import java.util.Objects;

public class Coordinates {
    private final int col, row;
    public Coordinates(int col, int row) {
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

    public ArrayList<Coordinates> getNeighbors() {


    }
}
