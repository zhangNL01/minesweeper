package nl.saxion.minesweeper.model;

import java.util.ArrayList;
import java.util.Objects;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ArrayList<Position> getAllAround() {
        var result = new ArrayList<Position>();
        result.add(new Position(row - 1, column - 1));//left top
        result.add(new Position(row - 1, column));//top
        result.add(new Position(row - 1, column + 1));//right top
        result.add(new Position(row, column + 1));//right
        result.add(new Position(row + 1, column + 1));//bottom right
        result.add(new Position(row + 1, column));//bottom
        result.add(new Position(row + 1, column - 1));//bottom left
        result.add(new Position(row, column - 1));//left
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            var casted = (Position) obj;
            return (casted.getRow() == this.row) && (casted.getColumn() == this.column);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
