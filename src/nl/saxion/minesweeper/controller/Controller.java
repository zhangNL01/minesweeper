package nl.saxion.minesweeper.controller;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import nl.saxion.minesweeper.model.*;
import nl.saxion.minesweeper.view.*;

public class Controller {
    private int row;
    private int col;
    private int numOfMine;
    private ModelMap modelMap;
    private MainBoard mainBoard;
    private LeaderBoard leaderBoard;
    private GameBoard gameBoard;
    private ViewMap viewMap;
    private int flagNum;
    private Timer timer;
    private TimerView timerView;
    private FlagCounter flagCounter;
    private boolean gameStarted;
    private RecordList recordList;

    public void setRecordList(RecordList recordList) {
        this.recordList = recordList;
    }

    public void run() {
        this.viewMap = new ViewMap(this);
        this.gameBoard = new GameBoard(this);
        this.leaderBoard = new LeaderBoard();
        this.mainBoard = new MainBoard(this, leaderBoard);
        this.mainBoard.show();
    }

    public void setLevel(Command level) {
        switch (level) {
            case EASY:
                createGame(9, 9, 10);
                break;
            case NORMAL:
                createGame(16, 16, 40);
                break;
            case HARD:
                createGame(30, 16, 99);
                break;
            case CUSTOM:
                new CustomAlert(this);
                break;
            case EXIT:
                System.exit(0);
        }
    }

    public void createGame(int r, int c, int m) {
        this.row = r;
        this.col = c;
        this.numOfMine = m;
        prepareGameBoard();
        showGameBoard();
    }

    private void prepareGameBoard() {
        this.gameStarted = false;
        this.flagNum = 0;
        this.viewMap.setSize(row, col);
        this.gameBoard.setBlockMap(viewMap);
        this.modelMap = new ModelMap(row, col, numOfMine);
        this.gameBoard.setSize(row, col);
        this.timer = new Timer();
        this.timerView = new TimerView(timer);
        this.gameBoard.setTimer(timer, timerView);
        this.flagCounter = new FlagCounter(numOfMine);
        gameBoard.setFlagCounter(flagCounter);
    }

    private void showGameBoard() {
        gameBoard.show();
        mainBoard.close();
    }

    public void blockListener(int row, int col, MouseButton mouseButton) {
        if (!gameStarted) {
            gameStarted = true;
            timer.startTimer();
            timerView.gameStart();
        }
        var blockClicked = modelMap.getBlock(row, col);
        if (mouseButton.equals(MouseButton.PRIMARY)) {
            if (blockClicked.isCovered()) {
                blockClicked.clicked();
                if (blockClicked instanceof Digit) {
                    var numOfMines = ((Digit) modelMap.getBlock(row, col)).getNumOfMines();
                    viewMap.getBlock(row, col).setNum(numOfMines);
                    if (numOfMines == 0)
                        autoExplore(row, col);
                } else if (blockClicked instanceof Mine) {
                    viewMap.getBlock(row, col).setBomb(true);
                    fail();
                }
            }
        }
        if (mouseButton.equals(MouseButton.SECONDARY)) {
            if (modelMap.getBlock(row, col).isFlagged()) {
                viewMap.getBlock(row, col).unFlagged();
                this.flagNum--;
                blockClicked.rightClicked();
            } else if (this.flagNum < numOfMine) {
                viewMap.getBlock(row, col).setFlag(true);
                this.flagNum++;
                blockClicked.rightClicked();
            }
            flagCounter.update(numOfMine - flagNum);
            if (flagNum == numOfMine) {
                var flaggedAll = true;
                var allMines = modelMap.getMines();
                for (int i = 0; i < numOfMine; i++) {
                    if (!allMines.get(i).isFlagged())
                        flaggedAll = false;
                }
                if (flaggedAll)
                    win();
            }
        }
    }

    private void fail() {
        timer.stopTimer();
        timerView.gameEnd();
        showAll();
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Oops");
        alert.headerTextProperty().set("You failed!");
        alert.showAndWait();
    }

    private void win() {
        timer.stopTimer();
        timerView.gameEnd();
        recordList.addRecord(new Record(this.row * this.col, this.numOfMine, this.timer.getTime()));
        showAll();
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulation");
        alert.headerTextProperty().set("You won!");
        alert.showAndWait();
    }

    private void showAll() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                var blockView = viewMap.getBlock(r, c);
                var blockModel = modelMap.getBlock(r, c);
                if (blockModel.isCovered()) {
                    if (blockModel instanceof Digit) {
                        if (blockModel.isFlagged()) {
                            blockView.setFlag(false);
                        } else {
                            var numOfMines = ((Digit) modelMap.getBlock(r, c)).getNumOfMines();
                            blockView.setNum(numOfMines);
                        }
                    } else {
                        if (!blockModel.isFlagged())
                            blockView.setBomb(((Mine) blockModel).isClicked());
                    }
                }
            }
        }
    }

    private void autoExplore(int r, int c) {
        var aroundList = modelMap.getAvailableAround(r, c);
        for (int i = 0; i < aroundList.size(); i++) {
            var p = aroundList.get(i);
            var block = modelMap.getBlock(p.getRow(), p.getColumn());
            if (block.isCovered() && !block.isFlagged()) {
                if (block instanceof Digit) {
                    blockListener(p.getRow(), p.getColumn(), MouseButton.PRIMARY);
                }
            }
        }
    }

    public void showRank() {
        leaderBoard.setRecordList(recordList);
        leaderBoard.show();
        leaderBoard.setVisible();
    }

}
