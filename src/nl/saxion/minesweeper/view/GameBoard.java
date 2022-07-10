package nl.saxion.minesweeper.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.saxion.minesweeper.controller.Controller;
import nl.saxion.minesweeper.model.Timer;


public class GameBoard {
    private Controller controller;
    private Stage stage;
    private int row;
    private int column;
    private Timer timer;
    private TimerView timerView;
    private ViewMap blockMap;
    private FlagCounter flagCounter;

    public GameBoard(Controller controller) {
        this.stage = new Stage();
        this.controller = controller;
    }

    public void setSize(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setBlockMap(ViewMap blockMap) {
        this.blockMap = blockMap;
    }

    public void setTimer(Timer timer, TimerView timerView) {
        this.timer = timer;
        this.timerView = timerView;
    }

    public void setFlagCounter(FlagCounter flagCounter) {
        this.flagCounter = flagCounter;
    }

    public void show() {
        try {
            var pane = new BorderPane();

            pane.setBackground(new Background(
                    new BackgroundFill(Color.rgb(74, 192, 253), CornerRadii.EMPTY, Insets.EMPTY)));

            var dataArea = new HBox();
            var timeText = new Text("Time:");
            timeText.setFill(Color.WHITE);
            timeText.setFont(Font.font(null, FontWeight.BOLD, 20));

            var flagIcon = new ImageView("file:./images/flaged.jpg");
            flagIcon.setFitHeight(20);
            flagIcon.setFitWidth(20);

            var x = new Text("✖");
            x.setFill(Color.WHITE);
            x.setFont(Font.font(null, FontWeight.NORMAL, 20));

            dataArea.getChildren().add(timeText);
            dataArea.getChildren().add(timerView);
            dataArea.getChildren().add(flagIcon);
            dataArea.getChildren().add(x);
            dataArea.getChildren().add(flagCounter);
            dataArea.setAlignment(Pos.CENTER);
            dataArea.setSpacing(10);
            pane.setTop(dataArea);
            pane.setCenter(blockMap);

            stage.setOnCloseRequest(e -> controller.run());

            Scene scene = new Scene(pane, getWidth(), getHeight());
            this.stage.setScene(scene);
            scene.setOnKeyPressed(e -> {
                if (e.getCode().equals(KeyCode.ESCAPE)) {
                    stage.close();
                    controller.run();
                }
            });
            this.stage.setTitle("New game");
            this.stage.getIcons().add(new Image("file:./images/mine.png"));
            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getWidth(){
        var baseRow = row * 20 + 30;
        if(baseRow < 210)
            return 210;
        else
            return baseRow;
    }

    private int getHeight(){
        var baseRow = column * 20 + 70;
        if(baseRow < 250)
            return 250;
        else
            return baseRow;
    }
}
