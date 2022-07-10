package nl.saxion.minesweeper.model;

import java.util.ArrayList;
import java.util.HashSet;

public class ModelMap {
    private Tile[][] map;
    private int row;
    private int col;
    private int numOfMine;
    private ArrayList<Mine> mines;

    public ModelMap(int row, int col, int numOfMine) {
        this.row = row;
        this.col = col;
        this.numOfMine = numOfMine;
        this.map = new Tile[row][col];
        fillMap();
    }

    private void fillMap() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                this.map[r][c] = new Digit();
            }
        }
        minePositions().stream().forEach(p ->
                this.map[p.getRow()][p.getColumn()] = new Mine());
        setNumOfMinesAround();
        createMineList();
    }

    private void createMineList() {
        this.mines = new ArrayList<>();
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (map[r][c] instanceof Mine)
                    this.mines.add((Mine) (map[r][c]));
            }
        }
    }

    private HashSet<Position> minePositions() {
        var locations = new HashSet<Position>();
        do {
            locations.add(new Position((int) (Math.random() * row), (int) (Math.random() * col)));
        }while (locations.size() < numOfMine);
        return locations;
    }

    public Tile getBlock(int row, int col) {
        return this.map[row][col];
    }

    private void setNumOfMinesAround() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (map[r][c] instanceof Digit) {
                    var count = 0;
                    var around = getAvailableAround(r, c);
                    for (int i = 0; i < around.size(); i++) {
                        var p = around.get(i);
                        if (map[p.getRow()][p.getColumn()] instanceof Mine)
                            count++;
                    }
                    ((Digit) map[r][c]).setNumOfMines(count);
                }
            }
        }
    }

    public ArrayList<Position> getAvailableAround(int r, int c) {
        var result = new ArrayList<Position>();
        var around = new Position(r, c).getAllAround();
        for (int i = 0; i < around.size(); i++) {
            var p = around.get(i);
            if ((p.getRow() >= 0 && p.getRow() < row) && (p.getColumn() >= 0 && p.getColumn() < col)) {
                result.add(p);
            }
        }
        return result;
    }

    public ArrayList<Mine> getMines() {
        return this.mines;
    }
}
