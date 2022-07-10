package nl.saxion.minesweeper.view;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import nl.saxion.minesweeper.controller.Command;
import nl.saxion.minesweeper.controller.Controller;

public class MenuButton extends Button {
    private Command command;
    private Controller controller;
    public MenuButton(Command command,Controller controller){
        this.command = command;
        this.controller = controller;
        this.setText(command.toString());
        this.setMinSize(10,1);
        this.setPrefSize(200,30);

        setAction();
    }
    private void setAction(){
        this.setOnMouseClicked(e -> controller.setLevel(command));
        this.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE)
                controller.setLevel(command);
        });
    }
}
