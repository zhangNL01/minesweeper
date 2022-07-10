package nl.saxion.minesweeper.view;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import nl.saxion.minesweeper.controller.Command;
import nl.saxion.minesweeper.controller.Controller;

public class MainBoard {
    private Controller controller;
    private final String background = "file:./images/background.png";
    private final String logo = "file:./images/logo.png";
    private Stage stage;
    private LeaderBoard leaderBoard;

    public MainBoard(Controller controller, LeaderBoard leaderBoard) {
        this.controller = controller;
        this.leaderBoard = leaderBoard;
        this.stage = new Stage();
    }

    public void show() {
        try {
            var root = new StackPane();
            var pane = new BorderPane();

            pane.setBackground(new Background(
                    new BackgroundImage(new Image(background),
                            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.CENTER,
                            new BackgroundSize(1,1,true,true,false,false))));

            var centerLogo = new HBox();
            centerLogo.getChildren().add(new ImageView(logo));
            centerLogo.setAlignment(Pos.CENTER);
            pane.setTop(centerLogo);

            var buttons = new Button[]{new MenuButton(Command.EASY,controller),
                    new MenuButton(Command.NORMAL,controller),
                    new MenuButton(Command.HARD,controller),
                    new MenuButton(Command.CUSTOM,controller),
                    new MenuButton(Command.EXIT,controller)};
            var buttonList = new VBox();
            buttonList.getChildren().addAll(buttons);
            buttonList.setAlignment(Pos.CENTER);
            pane.setCenter(buttonList);

            var rankIcon = new ImageView("file:./images/rank2.png");
            rankIcon.setOnMouseClicked(e -> controller.showRank());
            rankIcon.setOnMouseEntered(e -> {
                rankIcon.setImage(new Image("file:./images/rank.png"));
                rankIcon.setCursor(Cursor.HAND);
            });
            rankIcon.setOnMouseExited(e -> {
                rankIcon.setImage(new Image("file:./images/rank2.png"));
                rankIcon.setCursor(Cursor.DEFAULT);
            });
            pane.setRight(rankIcon);
            var fixPosition = new ImageView("file:./images/fix.png");
            pane.setLeft(fixPosition);

            pane.setOnKeyPressed(e ->{
                if(e.getCode() == KeyCode.ESCAPE)
                    controller.setLevel(Command.EXIT);
            });

            root.getChildren().add(pane);
            root.getChildren().add(leaderBoard);

            Scene scene = new Scene(root, 500, 300);
            this.stage.setScene(scene);
            this.stage.setTitle("Minesweeper");
            this.stage.getIcons().add(new Image("file:./images/mine.png"));
            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void close(){
        stage.close();
    }
}
