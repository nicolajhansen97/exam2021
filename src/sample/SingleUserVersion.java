package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;

public class SingleUserVersion {

    @FXML
    Button buttonTest;

    @FXML
    Rectangle boxTest;

    @FXML
    Pane boardP;
    BorderPane fuckU;

    StickyNote firstTest = new StickyNote();
    ArrayList<StickyNote> listTest = new ArrayList<>();

    @FXML
    public void handleColorChange(ActionEvent actionEvent) {
        firstTest.setColor(javafx.scene.paint.Color.RED);
        //boxTest.setFill(firstTest.getColor());
        listTest.add(new StickyNote());
        boardP.getChildren().addAll(listTest.get(0).getP());
       // boardP.setStyle("-fx-background-color: #1ea4a9");
    }






}
