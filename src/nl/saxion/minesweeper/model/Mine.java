package nl.saxion.minesweeper.model;

public class Mine implements Tile {
    private boolean covered;
    private boolean flagged;
    private boolean clicked;

    public Mine(){
        this.covered = true;
        this.flagged = false;
        this.clicked = false;
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
        this.clicked = true;
    }

    @Override
    public void rightClicked() {
        this.flagged = !this.flagged;
    }

    public boolean isClicked(){
        return this.clicked;
    }
}
