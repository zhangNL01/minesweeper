package nl.saxion.minesweeper.model;

public class Record {
    private int boardSize;
    private int mines;
    private long time;
    private int score;

    public Record(int boardSize, int mines, long time) {
        this.boardSize = boardSize;
        this.mines = mines;
        this.time = time;
        calculateScore();
    }

    private void calculateScore() {
        score = 10000 * boardSize * mines / (int) time;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getMines() {
        return mines;
    }

    public int getScore() {
        return score;
    }

}
