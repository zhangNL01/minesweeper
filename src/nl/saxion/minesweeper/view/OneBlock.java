package nl.saxion.minesweeper.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nl.saxion.minesweeper.controller.Controller;

public class OneBlock extends ImageView {
    public OneBlock(int row, int col, Controller controller) {
        super("file:./images/cover.png");
        this.setFitHeight(20);
        this.setFitWidth(20);
        var rowPosition = row;
        var columnPosition = col;
        this.setOnMouseClicked(e -> controller.blockListener(rowPosition, columnPosition, e.getButton()));
    }

    public void setNum(int num) {
        this.setImage(new Image("file:./images/" + num + ".jpg"));
    }

    public void setBomb(boolean clicked) {
        if (clicked)
            this.setImage(new Image("file:./images/bombClicked.jpg"));
        else
            this.setImage(new Image("file:./images/bomb.jpg"));
    }

    public void setFlag(boolean correct) {
        if (correct)
            this.setImage(new Image("file:./images/flaged.jpg"));
        else
            this.setImage(new Image("file:./images/wrongflag.jpg"));
    }

    public void unFlagged() {
        this.setImage(new Image("file:./images/cover.png"));
    }
}
