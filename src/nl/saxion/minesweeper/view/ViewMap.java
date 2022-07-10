package nl.saxion.minesweeper.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import nl.saxion.minesweeper.controller.Controller;

public class ViewMap extends GridPane {
    private OneBlock[][] map;
    private Controller controller;

    public ViewMap(Controller controller) {
        this.controller = controller;
    }

    public void setSize(int row, int column) {
        this.getChildren().clear();
        this.map = new OneBlock[row][column];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                var icon = new OneBlock(r, c, controller);
                map[r][c] = icon;
                this.add(map[r][c], r, c);
            }
        }

        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setMaxSize(row * 20, column * 20);
        this.setGridLinesVisible(true);
    }

    public OneBlock getBlock(int row, int col) {
        return this.map[row][col];
    }
}
