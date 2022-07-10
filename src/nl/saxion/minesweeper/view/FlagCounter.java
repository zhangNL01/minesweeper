package nl.saxion.minesweeper.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FlagCounter extends Label {
    public FlagCounter(int numOfMine) {
        this.setText(String.valueOf(numOfMine));
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setTextFill(Color.WHITE);
        this.setFont(new Font("Arial", 15));
    }

    public void update(int newNum) {
        this.setText(String.valueOf(newNum));
    }
}
