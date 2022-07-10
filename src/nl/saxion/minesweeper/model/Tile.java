package nl.saxion.minesweeper.model;

public interface Tile {
    boolean isCovered();
    boolean isFlagged();
    void clicked();
    void rightClicked();
}
