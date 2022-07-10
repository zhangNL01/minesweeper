package nl.saxion.minesweeper;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.saxion.minesweeper.controller.Controller;
import nl.saxion.minesweeper.model.RecordList;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var controller = new Controller();
        var recordList = new RecordList();

        controller.setRecordList(recordList);

        controller.run();
    }
}
