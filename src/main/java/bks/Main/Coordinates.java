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

//    public ArrayList<Coordinates> getNeighbors(Simulation simulation) {
//        ArrayList<Coordinates> listOfNeighbors = new ArrayList<>();
//        int i = this.row - 1;
//        int j = this.col;
//        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
//            if (simulation.isEmptyCeil(new Coordinates(i, j))) {
//                listOfNeighbors.add(new Coordinates(i, j));
//            }
//        }
//        i = this.row + 1;
//        j = this.col;
//        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
//            if (simulation.isEmptyCeil(new Coordinates(i, j))) {
//                listOfNeighbors.add(new Coordinates(i, j));
//            }
//        }
//        i = this.row;
//        j = this.col + 1;
//        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
//            if (simulation.isEmptyCeil(new Coordinates(i, j))) {
//                listOfNeighbors.add(new Coordinates(i, j));
//            }
//        }
//        i = this.row;
//        j = this.col - 1;
//        if (i < simulation.getHEIGHT() && i >= 0 && j < simulation.getWIDTH() && j >= 0) {
//            if (simulation.isEmptyCeil(new Coordinates(i, j))) {
//                listOfNeighbors.add(new Coordinates(i, j));
//            }
//        }
//        return listOfNeighbors;
//    }
}
