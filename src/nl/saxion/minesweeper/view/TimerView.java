package nl.saxion.minesweeper.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import nl.saxion.minesweeper.model.Timer;

public class TimerView extends Label {
    private Timer timer;
    private Timeline timeline;

    public TimerView(Timer timer) {
        this.timer = timer;
        this.setText(timer.getTimeFormatted());
        this.timeline = new Timeline();
        this.timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> updateTime()));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setTextFill(Color.WHITE);
        this.setFont(new Font("Arial", 20));
    }

    private void updateTime() {
        this.setText(timer.getTimeFormatted());
    }

    public void gameStart() {
        this.timeline.play();
    }

    public void gameEnd() {
        this.timeline.stop();
    }
}
