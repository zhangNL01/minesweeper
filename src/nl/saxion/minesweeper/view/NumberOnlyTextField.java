package nl.saxion.minesweeper.view;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class NumberOnlyTextField extends TextField {
    public NumberOnlyTextField() {
        this.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.BACK_SPACE))
                this.clear();
        });
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9]")) {
            super.replaceText(start, end, text);
        }
    }

}
