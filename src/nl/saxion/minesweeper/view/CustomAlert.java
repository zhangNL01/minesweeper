package nl.saxion.minesweeper.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import nl.saxion.minesweeper.controller.Controller;

public class CustomAlert extends Dialog {
    private Controller controller;

    public CustomAlert(Controller controller) {
        this.controller = controller;

        this.setTitle("New Custom Game");
        this.setHeaderText("Please enter your settings:");
        this.setGraphic(new ImageView("file:./images/mine.png"));

        var startButton = new ButtonType("Start", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().add(0, startButton);
        this.getDialogPane().getButtonTypes().add(1, ButtonType.CANCEL);
        var buttonSetting = this.getDialogPane().lookupButton(startButton);
        buttonSetting.setDisable(true);

        var pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(20, 150, 10, 10));

        var rowInput = new NumberOnlyTextField();
        var colInput = new NumberOnlyTextField();
        var mineInput = new NumberOnlyTextField();

        pane.add(new Text("Row:"), 0, 0);
        pane.add(rowInput, 1, 0);
        pane.add(new Text("Column:"), 0, 1);
        pane.add(colInput, 1, 1);
        pane.add(new Text("Mine:"), 0, 2);
        pane.add(mineInput, 1, 2);

        this.getDialogPane().setContent(pane);

        mineInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()) {
                var row = Integer.valueOf(rowInput.getText());
                var col = Integer.valueOf(colInput.getText());
                var mine = Integer.valueOf(mineInput.getText());
                buttonSetting.setDisable(mine >= (row * col - 1));
            }
        });

        this.setResultConverter(button -> {
            if (button == startButton) {
                var row = Integer.valueOf(rowInput.getText());
                var col = Integer.valueOf(colInput.getText());
                var mine = Integer.valueOf(mineInput.getText());
                this.controller.createGame(row, col, mine);
            }
            return null;
        });

        this.showAndWait();
    }
}
