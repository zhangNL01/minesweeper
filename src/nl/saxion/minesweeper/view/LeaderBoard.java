package nl.saxion.minesweeper.view;

import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import nl.saxion.minesweeper.model.RecordList;

public class LeaderBoard extends BorderPane{
    private RecordList recordList;

    public LeaderBoard() {
        this.setVisible(false);
    }

    public void setRecordList(RecordList recordList){
        this.recordList = recordList;
    }

    public void show(){
        this.setBackground(new Background(
                new BackgroundFill(Color.rgb(74, 192, 253), CornerRadii.EMPTY, Insets.EMPTY)));

        var centerLogo = new HBox();
        centerLogo.getChildren().add(new ImageView("file:./images/rankLogo.png"));
        centerLogo.setAlignment(Pos.CENTER);
        this.setTop(centerLogo);

        var recordsPane = new GridPane();
        var indexTitle = new Text("Ranking");
        fontSet(indexTitle);
        recordsPane.add(indexTitle,0,0);
        var sizeTitle = new Text("Map Size");
        fontSet(sizeTitle);
        recordsPane.add(sizeTitle,1,0);
        var mineTitle = new Text("Number of Mines");
        fontSet(mineTitle);
        recordsPane.add(mineTitle,2,0);
        var scoreTitle = new Text("Score");
        fontSet(scoreTitle);
        recordsPane.add(scoreTitle,3,0);
        var rs = recordList.getList();
        for(int i = 1; i <= rs.size(); i++){
            var index = new Text(String.valueOf(i));
            fontSet(index);
            recordsPane.add(index,0,i);
            var record = rs.get(i-1);
            var size = new Text(String.valueOf(record.getBoardSize()));
            fontSet(size);
            var mine = new Text(String.valueOf(record.getMines()));
            fontSet(mine);
            var score = new Text(String.valueOf(record.getScore()));
            fontSet(score);
            recordsPane.add(size,1,i);
            recordsPane.add(mine,2,i);
            recordsPane.add(score,3,i);
        }
        recordsPane.setAlignment(Pos.TOP_CENTER);
        recordsPane.setVgap(5);
        recordsPane.setHgap(15);
        this.setCenter(recordsPane);

        var centerButton = new HBox();
        var close = new Button("Close");
        close.setOnMouseClicked(e -> this.setVisible(false));
        close.setPrefSize(70, 40);
        centerButton.getChildren().add(close);
        centerButton.setAlignment(Pos.CENTER);
        this.setBottom(centerButton);
    }

    public void setVisible(){
        this.setVisible(true);
    }

    private void fontSet(Text text){
        text.setFill(Color.WHITE);
        text.setFont(Font.font(null, FontWeight.BOLD, 15));
    }
}
