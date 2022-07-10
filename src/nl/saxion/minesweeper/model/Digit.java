package nl.saxion.minesweeper.model;

public class Digit implements Tile {
    private boolean covered;
    private boolean flagged;
    private int numOfMines;

    public Digit(){
        this.covered = true;
        this.flagged = false;
        this.numOfMines = 0;
    }

    @Override
    public boolean isCovered() {
        return this.covered;
    }

    @Override
    public boolean isFlagged() {
        return this.flagged;
    }

    @Override
    public void clicked() {
        this.covered = false;
    }

    @Override
    public void rightClicked() {
        this.flagged = !this.flagged;
    }

    public void setNumOfMines(int num) {
        this.numOfMines = num;
    }
    public int getNumOfMines(){return this.numOfMines;}
}
