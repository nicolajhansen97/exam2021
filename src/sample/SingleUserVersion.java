package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SingleUserVersion {

    @FXML
    Pane pane;
    @FXML
    HBox hBox;

    @FXML
    BorderPane borderPane;

    StickyNote firstTest = new StickyNote();
    ArrayList<StickyNote> listTest = new ArrayList<>();
    int globalCount = 0;

    @FXML
    public void createNote() {

        listTest.add(new StickyNote());
        listTest.get(0).setCoordinate(globalCount,0);
        globalCount = globalCount+250;

        //borderPane.setCenter(listTest.get(0).createPane());

        pane.getChildren().addAll(listTest.get(0).createPane());
        //stackPane.getChildren().addAll(listTest.get(0).createPane());


        /*
        firstTest.setColor(javafx.scene.paint.Color.RED);
        //boxTest.setFill(firstTest.getColor());
        listTest.add(new StickyNote());
        boardP.getChildren().addAll(listTest.get(0).getP());
       // boardP.setStyle("-fx-background-color: #1ea4a9");
         */
    }
    //Works but need something better
    @FXML
    public void startMethod(){
        pane.getChildren().get(0).setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().get(0).setLayoutX(event.getX());
                pane.getChildren().get(0).setLayoutY(event.getY());

            }
        });
    }


}
