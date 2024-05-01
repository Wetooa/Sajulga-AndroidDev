package match3;


import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    private int row, col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }


    private int distance(Coordinate other) {
        return Math.abs(row - other.getRow()) + Math.abs(col - other.getCol());
    }



    public boolean isAdjacent(Coordinate other) {
        return distance(other) == 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Coordinate)) return false;
        Coordinate other = (Coordinate) obj;
        return row == other.getRow() && col == other.getCol();
    }

    @Override
    public int compareTo(Coordinate other) {
        if (row != other.getRow()) {
            return Integer.compare(row, other.getRow());
        }
        return Integer.compare(col, other.getCol());
    }


}
